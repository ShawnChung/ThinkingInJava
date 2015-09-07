package strings.ex8;

import strings.Splitting;

public class Ex8 {
	public static void main(String[] args) {
		String[] strings = Splitting.knights.split("the|you");
		for (String s : strings ) {
			System.out.print(s + " ");
		}
		
	}
}
