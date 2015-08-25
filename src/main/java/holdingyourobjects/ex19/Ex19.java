package holdingyourobjects.ex19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Ex19 {
	public static void main(String[] args) {
		Set<String> names = new HashSet<String>();
		names.add("Lenovo");
		names.add("Apple");
		names.add("Google");
		names.add("Microsoft");
		System.out.println("set: " + names);
		List<String> list = new ArrayList<String>(names);
		Collections.sort(list);
		System.out.println("list: " + list);
		Set<String> sortedNames = new LinkedHashSet<String>();
		for (String name : list) {
			sortedNames.add(name);
		}
		System.out.println(sortedNames);
	}
}
