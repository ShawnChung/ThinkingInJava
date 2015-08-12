package org.shawnana.thinkinginjava.innerclasses;

public class AnonymousConstructor {
	abstract class Base {
		public Base(int i) {
			System.out.println("Base constructor, i = " + i);
		}
		
		public abstract void f();
	}
	
	public static Base getBase(int i) {
		return new AnonymousConstructor().new Base(i) {
			{ System.out.println("Inside instance initializer"); 
				value = 0;
				//! value = i; in this case, i must be final
			}
			
			private int value;
			
			@Override
			public void f() {
				System.out.println("In anonymous f()");
			}
		};
	}
	
	public static void main(String[] args) {
		Base base = getBase(22);
		base.f();
	}
}
