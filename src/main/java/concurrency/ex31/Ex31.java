package concurrency.ex31;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex31 {
	public static void main(String[] args) throws InterruptedException, IOException {
		int ponder = 0;
		if (args.length > 0) 
			ponder = Integer.parseInt(args[0]);
		int size = 3;
		if (args.length > 1)
			size = Integer.parseInt(args[1]);
		ExecutorService exec = Executors.newCachedThreadPool();
		Bin bin = new Bin(5);
		for (int i = 0; i < 10; i++)
			exec.execute(new Philosopher(bin, i, ponder));
		if (args.length == 3 && args[2].equals("timeout"))
			TimeUnit.SECONDS.sleep(5);
		else {
			System.out.println("Press 'Enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}
