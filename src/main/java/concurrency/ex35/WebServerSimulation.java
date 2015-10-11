package concurrency.ex35;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Read only objects don't require synchronization
class Client {
	private final int serviceTime;
	public Client(int tm) {
		serviceTime = tm;
	}
	public int getServiceTime() {
		return this.serviceTime;
	}
	public String toString() {
		return "[" + serviceTime + "]";
	}
}

class ClientLine extends ArrayBlockingQueue<Client> {
	public ClientLine(int maxLineSize) {
		super(maxLineSize);
	}
	public String toString() {
		if (this.size() == 0) {
			return "[Empty]";
		}
		StringBuilder sb = new StringBuilder();
		for (Client client : this) {
			sb.append(client);
		}
		return sb.toString();
	}
}

class ClientGenerator implements Runnable {
	private ClientLine clients;
	private static Random rand = new Random(47);
	private int sleepingTime = 10000;
	public ClientGenerator(ClientLine cu) {
		this.clients = cu;
	}
	public void run() {
		try {
			while (sleepingTime > 0 && !Thread.interrupted()) {
				int sleeptime = rand.nextInt(300);
				sleepingTime -= sleeptime;
				TimeUnit.MILLISECONDS.sleep(sleeptime);
				clients.put(new Client(rand.nextInt(1000)));
			}
		} catch(InterruptedException e) {
			System.out.println("ClientGenerator interrupted");
		}
		System.out.println("ClientGenerator terminating");
	}
}

class RequestHandler implements Runnable, Comparable<RequestHandler> {
	private static int counter = 0;
	private final int id = counter++;
	private int clientsServed = 0;
	private ClientLine clients;
	private boolean servingClientLine = true;
	public RequestHandler(ClientLine cl) {
		this.clients = cl;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Client client = clients.take();
				TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
				synchronized(this) {
					clientsServed++;
					while (!servingClientLine) 
						wait();
				}
			}
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " terminating");
	}
	public synchronized void doSomethingElse() {
		clientsServed = 0;
		servingClientLine = false;
	}
	public synchronized void serveClientLine() {
		assert !servingClientLine:" already serving: " + this;
		servingClientLine = true;
		notifyAll();
	}
	public String toString() {
		return "RequestHandler" + id + " ";
	}
	public String shortString() {
		return "RH" + id;
	}
	public synchronized int compareTo(RequestHandler other) {
		return clientsServed < other.clientsServed ? -1 :
			(clientsServed == other.clientsServed ? 0 : 1);
	}
}

class RequestHandlerManager implements Runnable {
	private ExecutorService exec;
	private ClientLine clients;
	private PriorityQueue<RequestHandler> workingHandlers = 
			new PriorityQueue<RequestHandler>();
	private Queue<RequestHandler> tellersDoingOtherThings = new LinkedList<RequestHandler>();
	private int adjustmentPeriod;
	public RequestHandlerManager(ExecutorService e, ClientLine clients, int adjustmentPeriod) {
		this.exec = e;
		this.clients = clients;
		this.adjustmentPeriod =  adjustmentPeriod;
		RequestHandler handler = new RequestHandler(clients);
		exec.execute(handler);
		workingHandlers.add(handler);
	}
	public void adjustTellerNumber() {
		// This is actually a control system. By adjusting the numbers,
		// you can reveal stability issues
		// If line is too long, add another teller
		if (clients.size() / workingHandlers.size() > 2) {
			if (tellersDoingOtherThings.size() > 0) {
				RequestHandler handler = tellersDoingOtherThings.remove();
				handler.serveClientLine();
				workingHandlers.add(handler);
				return;
			}
			RequestHandler handler = new RequestHandler(clients);
			exec.execute(handler);
			workingHandlers.add(handler);
			return;
		}
		
		if (workingHandlers.size() > 1 && clients.size() / workingHandlers.size() < 2) {
			reassignOneHandler();
		}
		
		if (clients.size() == 0) {
			while (workingHandlers.size() > 1) {
				reassignOneHandler();
			}
		}
	}
	
	private void reassignOneHandler() {
		RequestHandler teller = workingHandlers.remove();
		teller.doSomethingElse();
		tellersDoingOtherThings.offer(teller);
	}
	
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.print(clients + "{");
				for (RequestHandler handler : workingHandlers)
					System.out.print(handler.shortString() + " ");
				System.out.println("}");
			}
		} catch(InterruptedException e) {
			System.out.println(this + " interrupted");
		}
		System.out.println(this + " terminating");
	}
	
	public String toString() {
		return "RequestHandlerManager";
	}
}

public class WebServerSimulation {
	static final int MAX_LINE_SIZE = 50;
	static final int ADJUSTMENT_PERIOD = 1000;
	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ClientLine clients = new ClientLine(MAX_LINE_SIZE);
		exec.execute(new ClientGenerator(clients));
		exec.execute(new RequestHandlerManager(exec, clients, ADJUSTMENT_PERIOD));
		System.out.println("Press 'Enter' to quit");
		System.in.read();
		exec.shutdownNow();
	}
}
