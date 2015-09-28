package concurrency.ex18;

import java.util.concurrent.TimeUnit;

public class NonTask {
	public static void sleep() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			System.out.println("InterruptException");
		}
	}
}
