package org.shawnana.thinkinginjava.innerclasses.ex7;

public class Outer {
	private int i = 11;
	
	private int getValue() {
		return i;
	}
	
	class Inner {
		int changeOuterValue(int newValue) {
			i = newValue;
			return getValue();
		}
	}
}
