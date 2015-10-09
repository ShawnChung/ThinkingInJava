package concurrency.ex31;

import java.util.LinkedList;

public class Bin {
	private final LinkedList<Chopstick> list = new LinkedList<Chopstick>();
	private final int capacity;
	public Bin(int capacity) {
		for (int i = 0; i < capacity; i++)
			list.offer(new Chopstick(i));
		this.capacity = capacity;
	}
	public synchronized ChopstickPair takePair(Philosopher philosopher) throws InterruptedException {
		while (list.size() < 2)
			wait();
		ChopstickPair pair = new ChopstickPair(list.remove(), list.remove());
		pair.getLeft().take();
		pair.getRight().take();
		System.out.println(philosopher + " takes chopsticks " + pair);
		return pair;
	}
	public synchronized void dropPair(ChopstickPair pair, Philosopher philosopher) {
		pair.getLeft().drop();
		pair.getRight().drop();
		list.offer(pair.getLeft());
		list.offer(pair.getRight());
		System.out.println(philosopher + " drops chopsticks " + pair);
		notifyAll();
	}
}

class ChopstickPair {
	private Chopstick left;
	private Chopstick right;
	public ChopstickPair(Chopstick left, Chopstick right) {
		this.left = left;
		this.right = right;
	}
	public Chopstick getLeft() {
		return left;
	}
	public Chopstick getRight() {
		return right;
	}
	public String toString() {
		return left + ":" + right;
	}
}
