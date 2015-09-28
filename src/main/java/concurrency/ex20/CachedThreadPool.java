package concurrency.ex20;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPool {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Task());
		}
		exec.shutdownNow();
	}
}

class Task implements Runnable {
	private static int count = 0;
	private final int id = count++;
	
	public Task() {
		System.out.println("#" + id + " constructs");
	}
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("#" + id + " Message 1");
		Thread.yield();
		System.out.println("#" + id + " Message 2");
		Thread.yield();
		System.out.println("#" + id + " Message 3");
		Thread.yield();
		return;
	}
}
