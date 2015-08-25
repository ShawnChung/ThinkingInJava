package holdingyourobjects.ex5;

import static utils.Print.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListFeatures {
	public static void main(String[] args) {
		Random rand = new Random(10);
		List<Integer> ints = getIntegerList(rand);
		print("1: " + ints );
		Integer i = new Integer(144444);
		ints.add(i);
		print("2: " + ints );
		print("3: " + ints.contains(i));
		//ints.remove(new Integer(String.valueOf(i))); 
		ints.remove(i);
		print("4: " + ints);
		Integer i1 = ints.get(2);
		print("5: " + i1 + " index: " + ints.indexOf(i1));
		Integer i2 = new Integer("22");
		print("6: " + ints.indexOf(i2));
		print("7: " + ints.remove(i2));
		print("8: " + ints.remove(i1));
		print("9: " + ints);
		ints.add(3, new Integer("222222"));
		print("10: " + ints);
		List<Integer> sub = ints.subList(1, 4);
		print("subList: " + sub);
		print("11: " + ints.containsAll(sub));
		Collections.sort(sub);
		print("sorted subList: " + sub);
		// Order is not important in containsAll()
		print("12: " + ints.containsAll(sub));
		Collections.shuffle(sub, rand);
		print("shuffle subList: " + sub);
		print("13: " + ints.containsAll(sub));
		List<Integer> copy = new ArrayList<Integer>(ints);
		sub = Arrays.asList(ints.get(1), ints.get(4));
		print("sub: " + sub);
		copy.retainAll(sub);
		print("14: " + copy);
		copy = new ArrayList<Integer>(ints);
		print("15: " + copy);
		copy.remove(2);
		print("16: " + copy);
		copy.removeAll(sub);
		print("17: " + copy);
		copy.set(1, new Integer("123456789"));
		print("18: " + copy);
		copy.addAll(2, sub);
		print("19: " + copy);
		print("20: " + ints.isEmpty());
		ints.clear();
		print("21: " + ints);
		print("22: " + ints.isEmpty());
		ints.addAll(getIntegerList(rand));
		print("23: " + ints);
		Object[] o = ints.toArray();
		print("24: " + o[3]);
		Integer[] in = ints.toArray(new Integer[0]);
		print("25: " + in[3]);
	}

	private static List<Integer> getIntegerList(Random rand) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(rand.nextInt());
		}
		return list;
	}
}


