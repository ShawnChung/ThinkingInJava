package holdingyourobjects.ex9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sequence {
	private List<Object> items;
	
	public Sequence() {
		items = new ArrayList<Object>();
	}
	
	public void add(Object x) {
		items.add(x);
	}
	
	/*private class ReverseSequenceSelector implements Selector{
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
		
	}*/
	
	public int size() {
		return this.items.size();
	}
	
	public Iterator<Object> iterator() {
		return this.items.iterator();
	}
	
	/*public Selector reverseSelector() {
		return new ReverseSequenceSelector();
	}*/
	
	public static void main(String[] args) {
		Sequence sequence = new Sequence();
		for (int i = 0; i < 10; i++) {
			sequence.add(Integer.toString(i));
		}
		Iterator iterator = sequence.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		
		/*Selector reverseSelector = sequence.reverseSelector();
		while (!reverseSelector.end()) {
			System.out.print(reverseSelector.current() + " ");
			reverseSelector.next();
		}*/
	}
}
