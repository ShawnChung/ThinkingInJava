package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex {
	private Lock lock = new ReentrantLock();
	public BlockedMutex() {
		// Acquire it right away, to demostrate interruption
		// of a task blocked on a ReentrantLock:
		lock.lock();
	}
	public void f() {
		try {
			// This will never be available to a second task
			lock.lockInterruptibly();
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	private BlockedMutex blocked = new BlockedMutex();
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
}

public class Interrupting2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
		
		/*Thread t = new Thread() {
			public void run() {
				while (true) {
					System.out.println("Thread is running");
				}
			}
		};
		t.start();
		TimeUnit.SECONDS.sleep(1);
		t.interrupt();*/
		/*
		 * Note that when you call interrupt() on a thread, the only time that 
		 * the interrupt occurs is when the tas kenters, or is already inside,
		 * a blocking operation(except-0-, as you've seen, in the case of uninterruptible
		 * I/O or clocked synchronized methods, in which case there's nothing you 
		 * can do)
		 */
	}
}
