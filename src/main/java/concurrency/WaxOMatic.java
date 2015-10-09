package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
	private volatile boolean waxOn = false;
	public synchronized void waxed() {
		waxOn = true;
		notifyAll();
	}
	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}
	public synchronized void waitingForWaxing() throws InterruptedException {
		while (waxOn == false)
			wait();
	}
	public synchronized void waitingForBuffing() throws InterruptedException {
		while (waxOn == true)
			wait();
	}
}

class WaxOn implements Runnable {
	private Car car;
	public WaxOn(Car car) {
		this.car = car;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax On! ");
				car.waitingForBuffing();
				TimeUnit.MILLISECONDS.sleep(500);
				car.waxed();

			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
	}
}

class WaxOff implements Runnable {
	private Car car;
	public WaxOff(Car car) {
		this.car = car;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax Off! ");

				car.waitingForWaxing();
				TimeUnit.MILLISECONDS.sleep(500);
				car.buffed();

			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
	}
}

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
