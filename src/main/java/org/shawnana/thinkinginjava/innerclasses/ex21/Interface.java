package org.shawnana.thinkinginjava.innerclasses.ex21;

public interface Interface {
	void method();
	
	class ClassInInterface {
		void callMethod(Interface interf) {
			interf.method();
		}
		
	}
}
