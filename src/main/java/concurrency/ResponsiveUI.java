package concurrency;

import java.io.IOException;

class UnResponsiveUI {
	private volatile double d = 1;
	public UnResponsiveUI() throws Exception {
		while (d > 0) {
			d = d + (Math.PI + Math.E) / d;
		}
		System.in.read(); // Never gets here
	}
}

public class ResponsiveUI extends Thread {
	private static volatile double d = 1;
	public ResponsiveUI() {
		this.setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		while (true) {
			d = d + (Math.PI + Math.E) / d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//! new UnResponsiveUI() // Must kill this process
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);
	}
}
