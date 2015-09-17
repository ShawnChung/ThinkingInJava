package concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	public static synchronized int nextSerialNumber() {
		return serialNumber++;
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Set<Integer> intSet = new HashSet<Integer>();
		for (int i = 0; i < 2; i++) {
			exec.execute(new Runnable() {

				@Override
				public void run() {
					while (true) {
						int val = SerialNumberGenerator.nextSerialNumber();
						if (intSet.contains(val)) {
							System.out.println(val);
							return;
						}
						intSet.add(val);
					}
				}
				
			});
		}
	}
}
