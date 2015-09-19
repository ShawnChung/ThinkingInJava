package concurrency.ex15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex15 {
	private final Object syncObject1 = new Object();
	private final Object syncObject2 = new Object();
	private final Object syncObject3 = new Object();
	
	public void f() {
		synchronized (syncObject1) {
			while (true) {
				System.out.println("f()");
			}
		}
	}
	
	public void g() {
		synchronized (syncObject2) {
			while (true) {
				System.out.println("g()");
			}
		}
	}
	
	public void h() {
		synchronized (syncObject3) {
			while (true) {
				System.out.println("h()");
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Ex15 ex15 = new Ex15();
		exec.execute(new Runnable() {
			@Override
			public void run() {
				ex15.f();
			}
		});
		exec.execute(new Runnable() {
			@Override
			public void run() {
				ex15.g();
			}
		});
		exec.execute(new Runnable() {
			@Override
			public void run() {
				ex15.h();
			}
		});
		exec.shutdown();
	}
}
