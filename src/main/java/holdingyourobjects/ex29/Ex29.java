package holdingyourobjects.ex29;

import java.util.PriorityQueue;

public class Ex29 {
	public static void main(String[] args) {
		PriorityQueue<Ex29> queue = new PriorityQueue<Ex29>();
		queue.offer(new Ex29());
		//! queue.offer(new Ex29());  // ClassCastException
	}
}
