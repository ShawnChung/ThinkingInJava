package concurrency.ex21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SecondRunnable implements Runnable {
	private FirstRunnable r;
	public SecondRunnable(FirstRunnable r) {
		this.r = r;
	}
	@Override
	public synchronized void run() {
		try {
			TimeUnit.SECONDS.sleep(5);
			//r.customNotifyAll();
			synchronized(r) {
				r.notifyAll();
			}
		} catch (InterruptedException e) {
			System.out.println("SecondRunnable's run interrupt");
		}
		
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		FirstRunnable first = new FirstRunnable();
		exec.execute(first);
		SecondRunnable second = new SecondRunnable(first);
		exec.execute(second);
		exec.shutdown();
	}
}
