package org.shawnana.thinkinginjava.innerclasses.ex11;

public class Outer {
	private class Inner implements Interface11 {

		public String getValue() {
			return "Inner";
		}
		
	}
	
	public Interface11 getInterface11() {
		return new Inner();
	}
	
	public static void main(String[] args) {
		Inner inner = (Inner) new Outer().getInterface11();
	}
}
