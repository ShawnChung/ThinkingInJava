package strings.ex20;

import java.util.Scanner;

public class Ex20 {
	private int i;
	private long l;
	private float f;
	private double d;
	private String s;
	
	public Ex20(String input) {
		// By default, Scanner splits input tokens along whitespace
		Scanner in = new Scanner(input);
		this.i = in.nextInt();
		this.l = in.nextLong();
		this.f = in.nextFloat();
		this.d = in.nextDouble();
		this.s = in.next();
	}
	
	public String toString() {
		return "i=" + i + " l=" + l + " f=" + f + " d=" + d + " s=" +s;
	}
	
	public static void main(String[] args) {
		Ex20 ex20 = new Ex20("1 50000 2 7 string");
		System.out.println(ex20);
	}
}
