package concurrency.ex18;

public class Task implements Runnable {
	public void run() {
		NonTask.sleep();
	}
	
	public static void main(String[] args) {
		Thread t = new Thread(new Task());
		t.start();
		t.interrupt();
	}
}
