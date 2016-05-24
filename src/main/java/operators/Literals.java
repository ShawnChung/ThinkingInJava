package operators;

public class Literals {
	public static void main(String[] args) {
		int i1 = 0x2f;
		System.out.println("i1 : " + Integer.toBinaryString(i1));
		int i2 = 0x2F;
		System.out.println("i2 : " + Integer.toBinaryString(i2));
		int i3 = 0177;
		System.out.println("i3 : " + Integer.toBinaryString(i3));
		char c = 0xffff;
		System.out.println("c : " + Integer.toBinaryString(c));
		byte b = 0x7f;
		System.out.println("b : " + Integer.toBinaryString(b));
		short s = 0x7fff; // Java中为有符号数，所以即使short与char都是16位，但是由于char不表示正负，因此范围是2的16次方减一，而short是2的15次方减一
		System.out.println("s : " + Integer.toBinaryString(s));
		long l1 = 200l;
		System.out.println("l1 : " + Long.toBinaryString(l1));
		long l2 = -200L;
		System.out.println("l2 : " + Long.toBinaryString(l2));
		long l3 = 200;
		float f1 = 1f;
		float f2 = 1F;
		float f3 = 1;
		double d1 = 1d;
		double d2 = 1D;
		
	}
}
