package org.shawnana.thinkinginjava.innerclasses.ex26;

public class Outer1 {
	class Inner1 {
		private String arg;
		Inner1(String arg) {
			this.arg = arg;
		}
	}
}

class Outer2 {
	class Inner2 extends Outer1.Inner1 {
		Inner2(Outer1 o1, String arg) {
			o1.super(arg);
		}
	}
}
