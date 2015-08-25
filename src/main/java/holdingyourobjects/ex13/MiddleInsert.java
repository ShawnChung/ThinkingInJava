package holdingyourobjects.ex13;

import java.util.LinkedList;
import java.util.Random;

public class MiddleInsert {
	
	public static void main(String[] args) {
		Random rand = new Random();
		LinkedList<Integer> llist = new LinkedList<Integer>();
		//ListIterator li = llist.listIterator(llist.size() / 2);
		for (int i = 0; i < 10; i++) {
			llist.add(llist.size() / 2, rand.nextInt(20));
			System.out.println(llist);
		}
		
	}
}
