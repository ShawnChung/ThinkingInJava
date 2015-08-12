package org.shawnana.thinkinginjava.innerclasses.ex8;

public class Outer {
	private class Inner {
		private int i = 11;
	}
	
	public int getInnerPrivateField() {
		return new Inner().i;
	}
	
	public static void main(String[] args) {
		Outer outer = new Outer();
		System.out.println(outer.getInnerPrivateField());
	}
}
