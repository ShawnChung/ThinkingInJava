package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsVarargs {
	public static <T> List<T> makeList(T... args) {
		List<T> result = new ArrayList<T>();
		for (T item : args) {
			result.add(item);
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<String> ls = makeList(new String[] {"A", "B", "C"});
		System.out.println(ls);
	}
}
