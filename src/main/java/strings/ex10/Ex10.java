package strings.ex10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex10 {
	public static void main(String[] args) {
		String phrase = "Java now has regular expressions";
		System.out.println(phrase);
		System.out.println(phrase.matches("^Java"));
		System.out.println(phrase.matches("\\Breg.*"));
		System.out.println(phrase.matches("n.w\\s+h(a|i)s"));
		
		Pattern p = Pattern.compile("^Java");
		Matcher m = p.matcher(phrase);
		System.out.println(m.matches());
	}
}
