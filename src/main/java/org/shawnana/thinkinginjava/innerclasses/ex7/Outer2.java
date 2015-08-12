package org.shawnana.thinkinginjava.innerclasses.ex7;

public class Outer2 {
	public static void main(String[] args) {
		Outer.Inner inner = new Outer().new Inner();
		System.out.println(inner.changeOuterValue(12));
	}
}
