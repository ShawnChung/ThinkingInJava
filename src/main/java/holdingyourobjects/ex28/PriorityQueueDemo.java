package holdingyourobjects.ex28;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueDemo {
	public static void main(String[] args) {
		 PriorityQueue<Double> dqueue = new  PriorityQueue<Double>();
		 Random rand = new Random();
		 for (int i = 0; i < 20; i++) {
			 dqueue.offer(rand.nextDouble() * 20);
		 }
		 System.out.println(dqueue);
		 while (!dqueue.isEmpty()) {
			 System.out.print(dqueue.poll() + " ");
		 }
	}
}
