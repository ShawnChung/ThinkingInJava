package strings.ex7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex7 {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("^[A-Z].+\\.$");
		Matcher m = p.matcher("dnsdon sdfs sdfw.");
		System.out.println(m.matches());
		
		
		System.out.println(Pattern.matches("^[A-Z].+\\.$", "Dnsdon sdfs sdfw."));
		
	}
}
