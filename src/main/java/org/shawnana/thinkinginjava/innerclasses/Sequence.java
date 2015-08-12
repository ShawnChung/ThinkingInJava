package org.shawnana.thinkinginjava.innerclasses;

public class Sequence {
	private Object[] items;
	private int next = 0;
	private int size;
	
	public Sequence(int size) {
		this.size = size;
		items = new Object[size];
	}
	
	public void add(Object x) {
		if (next < items.length)  {
			items[next] = x;
			next++;
		}
	}
	
	private class ReverseSequenceSelector implements Selector{
		private int i = items.length - 1;
		
		public boolean end() {
			return i < 0;
		}

		public Object current() {
			return items[i];
		}

		public void next() {
			if (i >= 0)
				i--;			
		}
		
	}
	
	private class SequenceSelector implements Selector {
		private int i = 0;
		public boolean end() {
			return i == items.length;
		}

		public Object current() {
			return items[i];
		}

		public void next() {
			if (i < items.length)
				i++;
		}
		
		public Sequence outer() {
			return Sequence.this;
			// return this.outer(); 死循环？
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public Selector selector() {
		return new SequenceSelector();
	}
	
	public Selector reverseSelector() {
		return new ReverseSequenceSelector();
	}
	
	public static void main(String[] args) {
		Sequence sequence = new Sequence(10);
		for (int i = 0; i < sequence.size(); i++) {
			sequence.add(Integer.toString(i));
		}
		Selector selector = sequence.selector();
		while (!selector.end()) {
			System.out.print(selector.current() + " ");
			selector.next();
		}
		Selector reverseSelector = sequence.reverseSelector();
		while (!reverseSelector.end()) {
			System.out.print(reverseSelector.current() + " ");
			reverseSelector.next();
		}
	}
}
