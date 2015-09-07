package strings;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerDelimiter {
	public static void main(String[] args) {
		Scanner scanner = new Scanner("12, 42, 78, 99, 42");
		//scanner.useDelimiter("\\s*,\\s*");
		scanner.useDelimiter(Pattern.compile("\\s*,\\s*"));
		while (scanner.hasNextInt())
			System.out.println(scanner.nextInt());
	}
}
