package holdingyourobjects.ex4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CharacterNameGenerator {
	private static final String[] charactorNames = {"A", "X", "N", "B", "I", "G"};
	private int currentIndex = 0;
	public String next() {
		String charactorName = charactorNames[currentIndex];
		currentIndex++;
		if (currentIndex >= charactorNames.length)
			currentIndex = 0;
		return charactorName;
	}
	
	public static void main(String[] args) {
			System.out.println(getCollection(new ArrayList<String>()));
			System.out.println(getCollection(new LinkedList<String>()));
			System.out.println(getCollection(new HashSet<String>()));
			System.out.println(getCollection(new LinkedHashSet<String>()));
			System.out.println(getCollection(new TreeSet<String>()));
	}
	
	public static Collection<String> getCollection(Collection<String> collection) {
		CharacterNameGenerator generator = new CharacterNameGenerator();
		for (int i = 0; i < 10; i ++) {
			collection.add(generator.next());
		}
		return collection;
	}
}
