package org.shawnana.thinkinginjava.innerclasses.ex13;

public class Outer {
	public Interface getInterface() {		
		return new Interface() {
			private String i = "inner";
			
			public String getValue() {
				return i;
			}
			
		};
	}
}
