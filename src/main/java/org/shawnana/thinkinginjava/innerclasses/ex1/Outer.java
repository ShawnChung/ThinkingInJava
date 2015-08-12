package org.shawnana.thinkinginjava.innerclasses.ex1;

public class Outer {
	Outer() {
		System.out.println("Outer");
	}
	
	class Inner {
		private Inner() {
			System.out.println("Inner");
		}
	}
	
	public Inner getInner() {
		return new Inner();
	}
	
	public static void main(String[] args) {
		Inner inner = new Outer().getInner();
	}
}
