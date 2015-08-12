package org.shawnana.thinkinginjava.innerclasses.ex21;

public class Implementation implements Interface {

	public void method() {
		System.out.println("Implementation method");
	}

	public static void main(String[] args) {
		Interface.ClassInInterface cii = new Interface.ClassInInterface();
		Interface im = new Implementation();
		cii.callMethod(im);
	}
}
