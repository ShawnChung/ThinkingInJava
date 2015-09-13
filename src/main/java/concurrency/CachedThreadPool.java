package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import concurrency.ex1.Ex1;

public class CachedThreadPool {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Ex1());
		}
		exec.shutdown();
	}
}
