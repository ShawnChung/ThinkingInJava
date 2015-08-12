package org.shawnana.thinkinginjava.innerclasses.ex2;

public class Outer2 {
	public Outer1.Inner getOuter1Inner() {
		return new Outer1().new Inner();
	}
}
