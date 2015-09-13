package concurrency.ex1;

public class Ex1 implements Runnable {
	private static int count = 0;
	private final int id = count++;
	
	public Ex1() {
		System.out.println("#" + id + " constructs");
	}
	@Override
	public void run() {
		System.out.println("#" + id + " Message 1");
		Thread.yield();
		System.out.println("#" + id + " Message 2");
		Thread.yield();
		System.out.println("#" + id + " Message 3");
		Thread.yield();
		return;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			new Thread(new Ex1()).start();
		}
	}
}
