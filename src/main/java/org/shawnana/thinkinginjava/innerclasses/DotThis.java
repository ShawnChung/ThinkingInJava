package org.shawnana.thinkinginjava.innerclasses;

public class DotThis {
	private int i;
	
	public DotThis(int i) {
		this.i = i;
	}
	
	void f() {
		System.out.println("DotThis.f(" + i + ") ");
	}
	
	class Inner {
		public DotThis outer() {
			return DotThis.this;
		}
	}
	
	public Inner inner() {
		return new Inner();
	}
	
	public static void main(String[] args) {
		DotThis dt = new DotThis(1);
		DotThis dt2 = new DotThis(2);
		DotThis.Inner dti = dt.inner();
		DotThis.Inner dti2 = dt2.new Inner();
		dti.outer().f(); 
		dti2.outer().f();
	}
}
