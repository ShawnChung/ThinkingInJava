package concurrency.ex8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import concurrency.DaemonThreadFactory;
import concurrency.LiftOff;

public class MoreBasicThreads {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}
