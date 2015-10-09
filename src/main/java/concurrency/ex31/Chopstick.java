package concurrency.ex31;

public class Chopstick {
	private final int id;
	private boolean taken = false;
	
	public Chopstick(int ident) {
		this.id = ident;
	}
	
	public synchronized void take() throws InterruptedException {
		while (taken)
			wait();
		taken = true;
	}
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
	public String toString() {
		return "#" + id;
	}
}
