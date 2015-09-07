package innerclasses;

public class Callbacks {
	public static void main(String[] args) {
		Callee1 c1 = new Callee1();
		Callee2 c2 = new Callee2();
		MyIncrement.f(c2);
		Caller caller1 = new Caller(c1);
		Caller caller2 = new Caller(c2.getCallbackReference());
		caller1.go();
		caller1.go();
		caller2.go();
		caller2.go();
		c2.increment();
	}
}

interface Incrementable {
	void increment();
}

class Callee1 implements Incrementable {
	private int i = 0;
	public void increment() {
		i++;
		System.out.println("Callee1 increment : " + i);
	}
}

class MyIncrement {
	public void increment() {
		System.out.println("MyIncrement increment");
	}
	static void f(MyIncrement mi) {
		mi.increment();
	}
}

class Callee2 extends MyIncrement {
	private int i = 0;
	public void increment() {
		super.increment();
		i++;
		System.out.println("Callee2 increment : " + i);
	}
	
	private class Closure implements Incrementable {
		public void increment() {
			Callee2.this.increment();
		}
	}
	
	Incrementable getCallbackReference() {
		return new Closure();
	}
}

class Caller {
	private Incrementable callbackReference;
	Caller(Incrementable callbackReference) {
		this.callbackReference = callbackReference;
	}
	void go() {
		callbackReference.increment();
	}
}
