package concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import generics.Generator;

class ExchangerProducer<T> implements Runnable {
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	ExchangerProducer(Exchanger<List<T>> exchg, Generator<T> gen, List<T>  holder) {
		exchanger = exchg;
		generator = gen;
		this.holder = holder;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				for (int i = 0; i < ExchangerDemo.size; i++) {
					holder.add(generator.next());
				}
				holder = exchanger.exchange(holder);
			}
		} catch(InterruptedException e) {
		}
	}
}

class ExchangerConsumer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
		this.exchanger = ex;
		this.holder = holder;
	}
	public void run() {
		try {
			while (!Thread.interrupted()) {
				holder = exchanger.exchange(holder);
				for (T x : holder) {
					value = x;
					holder.remove(x);
				}
			}
		} catch(InterruptedException e) {
		}
		System.out.println("Final value: " + value);
	}
}

public class ExchangerDemo {
	static int size = 10;
	static int delay = 5;
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
		List<Fat> producerList = new CopyOnWriteArrayList<Fat>(),
				consumerList = new CopyOnWriteArrayList<Fat>();
		//exec.execute(new ExchangerProducer<Fat>(xc, BasicGenerator., producerList));
		
	}
}


