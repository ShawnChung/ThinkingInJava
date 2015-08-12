package org.shawnana.thinkinginjava.innerclasses.ex9;

public class Outer {
	public Interface getInterface() {
		class Inner implements Interface {

			public String getValue() {
				return "Inner";
			}
			
		}
		
		return new Inner();
	}
}
