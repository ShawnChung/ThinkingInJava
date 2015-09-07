package generics.ex4;

import java.util.ArrayList;
import java.util.List;

import innerclasses.Selector;

public class Sequence<T> {
	private List<T> items;
	private int next = 0;
	
	public Sequence() {
		items = new ArrayList<T>();
	}
	
	public void add(T x) {
		items.add(x);
		next++;
	}
	
	private class ReverseSequenceSelector implements Selector{
		private int i = items.size() - 1;
		
		public boolean end() {
			return i < 0;
		}

		public T current() {
			return items.get(i);
		}

		public void next() {
			if (i >= 0)
				i--;			
		}
		
	}
	
	private class SequenceSelector implements Selector {
		private int i = 0;
		public boolean end() {
			return i == items.size();
		}

		public T current() {
			return items.get(i);
		}

		public void next() {
			if (i < items.size())
				i++;
		}
		
		public Sequence outer() {
			return Sequence.this;
			// return this.outer(); 死循环？
		}
	}
	
	public int size() {
		return this.items.size();
	}
	
	public Selector selector() {
		return new SequenceSelector();
	}
	
	public Selector reverseSelector() {
		return new ReverseSequenceSelector();
	}
	
	public static void main(String[] args) {
		Sequence<String> sequence = new Sequence<String>();
		for (int i = 0; i < 10; i++) {
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
