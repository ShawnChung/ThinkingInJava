package org.shawnana.thinkinginjava.innerclasses;

public class MultiInterfaces {
	static void takeA(A a) {}
	static void takeB(B b) {}
	
	public static void main(String[] args) {
		X x = new X();
		Y y = new Y();
		takeA(x);
		takeA(y);
		takeB(x);
		takeB(y.makeB());
	}
}

interface A {}

class X implements A, B {
	
}

interface B {}

class Y implements A {
	B makeB() {
		return new B() {
			
		};
	}
}