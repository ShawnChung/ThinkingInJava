package concurrency.ex22;

import java.util.concurrent.TimeUnit;

class TaskOne implements Runnable {
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(500);
				while (Flag.getFlag()) ;
				Flag.setFlag(true);
			}
		} catch (InterruptedException e) {
			System.out.println("TaskOne exiting via interrupt");
		}
	}
}

class TaskOneAdvance implements Runnable {
	private Flag flag;
	public TaskOneAdvance(Flag flag) {
		this.flag = flag;
	}
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(500);
				synchronized(flag) {
					flag.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("TaskOne exiting via interrupt");
		}
	}
}

class TaskTwo implements Runnable {
	public void run() {
		long f = 0L;
		for (int i = 0; i < 20; i++) {
			long start = System.currentTimeMillis();
			while (!Flag.getFlag()) ;
			Flag.setFlag(false);
			f += (System.currentTimeMillis() - start);
			System.out.println("finished " + i);
		}
		System.out.println("cost " + f);
	}
}

class TaskTwoAdvance implements Runnable {
	private Flag flag;
	public TaskTwoAdvance(Flag flag) {
		this.flag = flag;
	}
	public void run() {
		long f = 0L;
		for (int i = 0; i < 20; i++) {
			long start = System.currentTimeMillis();
			synchronized(flag) {
				try {
					flag.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			f += (System.currentTimeMillis() - start);
			System.out.println("finished " + i);
		}
		System.out.println("cost " + f);
	}
}

public class Ex22 {
	public static void main(String[] args) {
		Thread daemon = new Thread(new TaskOne());
		daemon.setDaemon(true);
		daemon.start();
		Thread taskTwo = new Thread(new TaskTwo());
		taskTwo.start();
		
		Flag flag = new Flag();
		Thread daemon2 = new Thread(new TaskOneAdvance(flag));
		daemon2.setDaemon(true);
		daemon2.start();
		Thread taskTwo2 = new Thread(new TaskTwoAdvance(flag));
		taskTwo2.start();
	}
}
