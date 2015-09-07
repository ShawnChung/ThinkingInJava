package strings.ex9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import strings.Splitting;

public class Ex9 {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("[aeiou]");
		Matcher m = p.matcher(Splitting.knights);
		System.out.println(m.replaceAll("_"));
	}
}
