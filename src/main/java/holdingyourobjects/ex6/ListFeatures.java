package holdingyourobjects.ex6;

import static utils.Print.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListFeatures {
	public static void main(String[] args) {
		Random rand = new Random(10);
		List<String> strings = getStringList(rand);
		print("1: " + strings );
		String s = "287364";
		strings.add(s);
		print("2: " + strings);
		print("3: " + strings.contains(s));
		//ints.remove(new Integer(String.valueOf(i))); 
		strings.remove(s);
		print("4: " + strings);
		String s1 = strings.get(2);
		print("5: " + s1 + " index: " + strings.indexOf(s1));
		String s2 = "s2";
		print("6: " + strings.indexOf(s2));
		print("7: " + strings.remove(s2));
		print("8: " + strings.remove(s1));
		print("9: " + strings);
		strings.add(3, "weirsdfs");
		print("10: " + strings);
		List<String> sub = strings.subList(1, 4);
		print("subList: " + sub);
		print("11: " + strings.containsAll(sub));
		Collections.sort(sub);
		print("sorted subList: " + sub);
		// Order is not important in containsAll()
		print("12: " + strings.containsAll(sub));
		Collections.shuffle(sub, rand);
		print("shuffle subList: " + sub);
		print("13: " + strings.containsAll(sub));
		List<String> copy = new ArrayList<String>(strings);
		sub = Arrays.asList(strings.get(1), strings.get(4));
		print("sub: " + sub);
		copy.retainAll(sub);
		print("14: " + copy);
		copy = new ArrayList<String>(strings);
		print("15: " + copy);
		copy.remove(2);
		print("16: " + copy);
		copy.removeAll(sub);
		print("17: " + copy);
		copy.set(1, "sdfuwenfos");
		print("18: " + copy);
		copy.addAll(2, sub);
		print("19: " + copy);
		print("20: " + strings.isEmpty());
		strings.clear();
		print("21: " + strings);
		print("22: " + strings.isEmpty());
		strings.addAll(getStringList(rand));
		print("23: " + strings);
		Object[] o = strings.toArray();
		print("24: " + o[3]);
		String[] in = strings.toArray(new String[0]);
		print("25: " + in[3]);
	}

	private static List<String> getStringList(Random rand) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add(String.valueOf(rand.nextInt()));
		}
		return list;
	}
}


