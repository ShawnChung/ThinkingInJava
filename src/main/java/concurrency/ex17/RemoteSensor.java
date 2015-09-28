package concurrency.ex17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RemoteSensor implements Runnable {
	private static RadiationCounter counter = new RadiationCounter();
	private static List<RemoteSensor> sensors = new ArrayList<RemoteSensor>();
	private static volatile boolean stop = false;
	private final int id;
	private volatile int number = 0;
	public RemoteSensor(int id) {
		this.id = id;
		sensors.add(this);
	}
	@Override
	public void run() {
		while (!stop) {
			synchronized(this) {
				++number;
			}
			System.out.println(this + " counting: " + number);
			counter.add();
		}
		System.out.println(this + " stopping");
	}
	
	public String toString() {
		return "#" + id;
	}
	
	public static void stop() {
		stop = true;
	}
	
	public synchronized int getNumber() {
		return number;
	}
	
	public static int getTotalCount() {
		return counter.getCount();
	}
	
	public static int sumNumber() {
		int sum = 0;
		for (RemoteSensor sensor : sensors) {
			sum += sensor.getNumber();
		}
		return sum;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new RemoteSensor(i));
		}
		exec.shutdown();
		TimeUnit.SECONDS.sleep(5);
		RemoteSensor.stop();
		if (!exec.awaitTermination(300, TimeUnit.MILLISECONDS))
			System.out.println("There are remote sensor still working!");
		System.out.println(RemoteSensor.getTotalCount());
		System.out.println(RemoteSensor.sumNumber());
	}
}
