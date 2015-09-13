package concurrency.ex9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
	private static int ids = 1;
	private final int id = ids++;
	private int countDown = 5;
	private volatile double d;
	
	@Override
	public String toString() {
		return Thread.currentThread() + " #" + id + ": " + countDown;
	}
	
	@Override
	public void run() {
		//Thread.currentThread().setPriority(priority);
		while (true) {
			for (int i = 1; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield();
			}
			System.out.println(this);
			if (--countDown == 0) return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new MinPriorityThreadFactory());
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities());
		}
		exec.shutdown();
		
		exec = Executors.newCachedThreadPool(new MaxPriorityThreadFactory());
		exec.execute(new SimplePriorities());
		exec.shutdown();
	}
}
