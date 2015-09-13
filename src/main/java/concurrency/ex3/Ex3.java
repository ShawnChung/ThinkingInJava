package concurrency.ex3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import concurrency.ex1.Ex1;

public class Ex3 implements Runnable {
	private static int count = 0;
	private final int id = count++;
	
	public Ex3() {
		System.out.println("#" + id + " constructs");
	}
	
	@Override
	public void run() {
		System.out.println("#" + id + " Message 1");
		Thread.yield();
		System.out.println("#" + id + " Message 2");
		Thread.yield();
		System.out.println("#" + id + " Message 3");
		Thread.yield();
		return;
	}
	
	public static void main(String[] args) {
		runWithCachedThreadPool();
		runWithFixedThreadPool(3);
		runWithSingleThreadExecutor();
	}
	
	static void runWithExcutorService(ExecutorService exec) {
		for (int i = 0; i < 5; i++)
			exec.execute(new Ex3());
		exec.shutdown();
	}
	
	static void runWithCachedThreadPool() {
		runWithExcutorService(Executors.newCachedThreadPool());
	}
	
	static void runWithFixedThreadPool(int nThreads) {
		runWithExcutorService(Executors.newFixedThreadPool(nThreads));
	}
	
	static void runWithSingleThreadExecutor() {
		runWithExcutorService(Executors.newSingleThreadExecutor());
	}
}
