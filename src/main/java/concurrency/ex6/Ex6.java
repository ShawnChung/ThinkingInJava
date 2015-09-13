package concurrency.ex6;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import concurrency.LiftOff;

public class Ex6 extends LiftOff {
	private static Random random = new Random(47);
	
	@Override
	public void run() {
		while(countDown > 0) {
			countDown--;
			System.out.print(status());
			try {
				TimeUnit.SECONDS.sleep(random.nextInt(11));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Ex6());
		}
		exec.shutdown();
	}
}
