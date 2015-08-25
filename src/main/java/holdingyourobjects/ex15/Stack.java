package holdingyourobjects.ex15;

import java.util.LinkedList;

public class Stack<T> {
	private LinkedList<T> storage = new LinkedList<T>();

	public void push(T v) {
		storage.addFirst(v);
	}

	public T peek() {
		return storage.getFirst();
	}

	public T pop() {
		return storage.removeFirst();
	}

	public boolean empty() {
		return storage.isEmpty();
	}

	public String toString() {
		return storage.toString();
	}
	
	public void clear() {
		this.storage.clear();
	}
	
	public static void main(String[] args) {
		String code = "+U+n+c-+e+r+t-+a-+i-+n+t+y-+-+r+u-+l+e+s-";
		Stack<Character> charStack = new Stack<Character>();		
		System.out.println();
		charStack.clear();
		for (int i = 0; i < code.length(); i++) {
			char c = code.charAt(i);
			if (c == '+' && i<code.length() - 1) {
				charStack.push(code.charAt(i+1));
				i++;
			}
			if (c == '-') {
				System.out.print(charStack.pop());
			}
		}
	}
}