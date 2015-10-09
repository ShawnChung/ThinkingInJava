package concurrency.ex28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import concurrency.LiftOff;

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		this.rockets = queue;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch(InterruptedException e) {
			System.out.println("Waking from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}
}

class LiftOffFillor implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffFillor(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}
	
	@Override
	public void run() {
		Random rand = new Random(47);
		try {
			while (true) {
				rockets.add(new LiftOff(rand.nextInt(5)));
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Stop fillor");
		}
	}
	
}

public class TestBlockingQueues {
	static void getKey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	static void getKey(String message) {
		System.out.println(message);
		getKey();
	}
	
	static void test(String msg, BlockingQueue<LiftOff> queue) throws InterruptedException {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		LiftOffFillor fillor = new LiftOffFillor(queue);
		/*ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(runner);
		exec.execute(fillor);*/
		Thread t = new Thread(runner) {
			public String toString() {
				return "1";
			}
		};
		t.start();
		Thread t2 = new Thread(fillor) {
			public String toString() {
				return "2";
			}
		};
		t2.start();
		//getKey("Press 'Enter' (" + msg + ")");
		TimeUnit.SECONDS.sleep(1);
		t2.interrupt();
		TimeUnit.SECONDS.sleep(10);
		t.interrupt();
		System.out.println("Finished " + msg + " test");
	}
	
	public static void main(String[] args) throws InterruptedException {
		test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>()); // Unlimited size
		//test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(5)); // fixed size
		//test("SynchronousQueue", new SynchronousQueue<LiftOff>()); // Size of 1
	}
}
