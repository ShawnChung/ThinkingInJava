package org.shawnana.thinkinginjava.innerclasses;

public class Factories {
	public static void serviceConsumer(ServiceFactory fact) {
		Service service = fact.getService();
		service.method1();
		service.method2();
	}
	
	public static void main(String[] args) {
		serviceConsumer(Implementation1.factory());
		serviceConsumer(Implementation2.factory);
	}
}
