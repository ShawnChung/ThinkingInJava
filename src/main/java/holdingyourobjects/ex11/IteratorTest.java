package holdingyourobjects.ex11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class IteratorTest {
	public static void main(String[] args) {
		Random rand = new Random();
		ArrayList<String> list = getStringList(rand);
		LinkedList<String> linkedList = new LinkedList<String>(list);
		HashSet<String> set = new HashSet<String>(list);
		TreeSet<String> tset = new TreeSet<String>(list);
		goThroughCollection(list);
		goThroughCollection(linkedList);
		goThroughCollection(set);
		goThroughCollection(tset);
	}
	
	public static void goThroughCollection(Collection<String> collection) {
		Iterator<String> iterator = collection.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
	}
	
	private static ArrayList<String> getStringList(Random rand) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add(String.valueOf(rand.nextInt()));
		}
		return list;
	}
}
