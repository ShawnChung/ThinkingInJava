package concurrency.ex16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ex16 {
	// private final Object syncObject1 = new Object();
	// private final Object syncObject2 = new Object();
	// private final Object syncObject3 = new Object();
	private final Lock lock1 = new ReentrantLock();
	private final Lock lock2 = new ReentrantLock();
	private final Lock lock3 = new ReentrantLock();

	public void f() {
		lock1.lock();
		try {
			while (true) {
				System.out.println("f()");
			}
		} finally {
			lock1.unlock();
		}
	}

	public void g() {
		lock2.lock();
		try {
			while (true) {
				System.out.println("g()");
			}
		} finally {
			lock2.unlock();
		}
	}

	public void h() {
		lock3.lock();
		try {
			while (true) {
				System.out.println("h()");
			}
		} finally {
			lock3.unlock();
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Ex16 ex16 = new Ex16();
		exec.execute(new Runnable() {
			@Override
			public void run() {
				ex16.f();
			}
		});
		exec.execute(new Runnable() {
			@Override
			public void run() {
				ex16.g();
			}
		});
		exec.execute(new Runnable() {
			@Override
			public void run() {
				ex16.h();
			}
		});
		exec.shutdown();
	}
}
