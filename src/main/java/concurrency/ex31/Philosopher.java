package concurrency.ex31;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
	/*private Chopstick left;
	private Chopstick right;*/
	private Bin bin;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);
	private void pause() throws InterruptedException {
		if (ponderFactor == 0) return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}
	public Philosopher(Bin bin, int ident, int ponder) {
		this.bin = bin;
		this.id = ident;
		this.ponderFactor = ponder;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				//System.out.println(this + " thingking");
				//pause();
				ChopstickPair chopsticks = bin.takePair(this);
				System.out.println(this + " eating");
				//pause();
				bin.dropPair(chopsticks, this);
			} 
		} catch(InterruptedException e) {
			System.out.println(this + " exiting via interrupt");
		}
	}
	public String toString() {
		return "Philosopher " + id;
	}
}
