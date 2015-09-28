
package concurrency.ex21;

public class FirstRunnable implements Runnable {
	public synchronized void run() {
		try {
			wait();
		} catch (InterruptedException e) {
			System.out.println("Exiting via Interrupt");
		}
		System.out.println("Awaked by notify");
	}
	
	public synchronized void customNotifyAll() {
		notifyAll();
	}
}
