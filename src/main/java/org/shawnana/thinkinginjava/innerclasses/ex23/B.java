package org.shawnana.thinkinginjava.innerclasses.ex23;

public class B {
	B(int length) {
		us = new U[length];
	}
	
	private U[] us;
	
	public void replaceU(U u, int index) {
		if (index >= 0 && index < us.length)
			us[index] = u;
	}
	
	public void removeU(int index) {
		if (index >= 0 && index < us.length)
			us[index] = null;
	}
	
	public void goThrough() {
		for (U u : us) {
			if (u != null) {
				u.method1();
				u.method2();
				u.method3();
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
