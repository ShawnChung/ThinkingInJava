package strings.ex5;

import java.util.Formatter;

public class Ex5 {
	public static void main(String[] args) {
		Formatter f = new Formatter(System.out);
		f.format("%0$-5d", 2, 3);
		f.format("%1$-5d", 2, 3);
		f.format("%2$-5d", 2, 3);
		
		f.format("%0$-5c", 'c');
		f.format("%0$-5.3b", true);
		f.format("%0$-5.2s", "String");
		f.format("%0$-20.3f", 23.22);
		f.format("%0$-20.4e", 23.22);
		f.format("%0$-5x", 22);
		f.format("%0$-5h", 22);
		f.format("%0$%", 22);
	}
}
