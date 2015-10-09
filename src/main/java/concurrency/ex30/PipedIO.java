package concurrency.ex30;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Sender implements Runnable {
	private Random rand = new Random(47);
	private BlockingQueue<Character> out; // = new LinkedBlockingQueue<Character>();
	public Sender(BlockingQueue<Character> queue) {
		this.out = queue;
	}
	@Override
	public void run() {
		try {
			while (true) {
				for(char c = 'A'; c <= 'Z'; c++) {
					out.put(c);;
					System.out.println("Write: " + c + ",");
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e + " Sender write exception");
		}
	}
}

class Receiver implements Runnable {
	private BlockingQueue<Character> queue;
	public Receiver(BlockingQueue<Character> queue) {
		this.queue = queue;
	}
	public void run() {
		try {
			while (true) {
				System.out.println("Read: " + (char) queue.take() + ", ");
			}
		} catch(InterruptedException e) {
			System.out.println(e + " Receiver read exception");
		}
	}
}

public class PipedIO {
	public static void main(String[] args) throws IOException, InterruptedException {
		BlockingQueue<Character> queue = new LinkedBlockingQueue<Character>();
		Sender sender = new Sender(queue);
		Receiver receiver = new Receiver(queue);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}
