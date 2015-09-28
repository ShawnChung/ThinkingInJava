package concurrency.ex17;

public class RadiationCounter {
	private volatile int count = 0; 
	public synchronized void add() {
		count++;
	}

	public synchronized int getCount() {
		return count;
	}

}
