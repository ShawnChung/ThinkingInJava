package concurrency.ex29;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED, PEANUTED, JELLYED
	}
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) {
		this.id = idn;
	}
	public void butter() {
		status = Status.BUTTERED;
	}
	public void jam() {
		status = Status.JAMMED;
	}
	public void peanut() {
		status = Status.PEANUTED;
	}
	public void jelly() {
		status = Status.JELLYED;
	}
	public Status getStatus() {
		return status;
	}
	public int getId() {
		return id;
	}
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
	
}

class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue tq) {
		toastQueue = tq;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
				toastQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster off");
	}
}

class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;
	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				// Blocks until next piece of toast is available
				Toast t = dryQueue.take();
				t.butter();
				System.out.println(t);
				butteredQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Butterer interrupted");
		}
		System.out.println("Butterer off");
	}
}

class Jammer implements Runnable {
	private ToastQueue butteredQueue, jamedQueue;
	public Jammer(ToastQueue buttered, ToastQueue jamed) {
		butteredQueue = buttered;
		jamedQueue = jamed;
	}
	
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = butteredQueue.take();
				t.jam();
				System.out.println(t);
				jamedQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Jammer interrupted");
		}
		System.out.println("Jammer off");
	}
}

class Peanuter implements Runnable {
	private ToastQueue peanutedQueue;
	private ToastQueue jamedQueue;
	public Peanuter(ToastQueue jamed, ToastQueue peanuted) {
		this.jamedQueue = jamed;
		this.peanutedQueue = peanuted;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = jamedQueue.take();
				t.peanut();
				System.out.println(t);
				peanutedQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Peanuter interrupt");
		}
		System.out.println("Peanuter off");
	}
}

class Jellyer implements Runnable {
	private ToastQueue peanutedQueue;
	private ToastQueue finishedQueue;
	public Jellyer(ToastQueue peanuted, ToastQueue finished) {
		this.peanutedQueue = peanuted;
		this.finishedQueue = finished;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = peanutedQueue.take();
				t.jelly();
				System.out.println(t);
				finishedQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Jellyer interrupt");
		}
		System.out.println("Jellyer off");
	}
}

class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;
	public Eater(ToastQueue finished) {
		finishedQueue = finished;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if (t.getId() != counter++ || t.getStatus() != Toast.Status.JELLYED) {
					System.out.println(">>>> Error: " + t);
					System.exit(1);
				} else {
					System.out.println("Chomp! " + t);
				}
			}
		} catch(InterruptedException e) {
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater off");
	}
}

public class ToastOmatic {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(),
				butteredQueue = new ToastQueue(),
				jamedQueue = new ToastQueue(),
				peanutedQueue = new ToastQueue(),
				finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, jamedQueue));
		exec.execute(new Peanuter(jamedQueue, peanutedQueue));
		exec.execute(new Jellyer(peanutedQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
