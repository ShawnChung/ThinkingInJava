package holdingyourobjects.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class ListIteratorTest {
	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> list = getIntegerList(rand);
		List<Integer> reverseList = new ArrayList<Integer>();
		for (int i = list.size() - 1; i >= 0; i--) {
			reverseList.add(list.get(i));
		}
		System.out.println(list);
		System.out.println(reverseList);
		reverseList.clear();
		
		ListIterator<Integer> listIterator = list.listIterator(list.size());
		while (listIterator.hasPrevious()) {
			reverseList.add(listIterator.previous());
		}
		System.out.println(list);
		System.out.println(reverseList);
	}
	
	private static List<Integer> getIntegerList(Random rand) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(rand.nextInt());
		}
		return list;
	}
}


