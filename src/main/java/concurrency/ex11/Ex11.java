package concurrency.ex11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex11 {
	private int i = 0;
	private boolean canceled = false;
	
	public synchronized int fouble() {
		i++;
		Thread.yield();
		i++;
		return i;
	}
	
	public boolean isCanceled() {
		return canceled;
	}
	
	public void cancel() {
		this.canceled = true;
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Ex11 ex11 = new Ex11();
		exec.execute(new Ex11Checker(ex11));
		exec.execute(new Ex11Checker(ex11));
		exec.shutdown();
	}

}

class Ex11Checker implements Runnable {
	private Ex11 ex11;
	public Ex11Checker(Ex11 ex11) {
		this.ex11 = ex11; 
	}
	
	@Override
	public void run() {
		while (!ex11.isCanceled()) {
			int val = ex11.fouble();
			if (val % 2 != 0) {
				System.out.println(val + " % 2 != 0");
				ex11.cancel();
			}
			System.out.println(val);
		}
	}
	
}
