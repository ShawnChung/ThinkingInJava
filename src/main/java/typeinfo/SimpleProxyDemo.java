package typeinfo;

public class SimpleProxyDemo {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("Zoom Zoom");
	}
	
	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}
}

interface Interface {
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface {
	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("somethingElse " + arg);
	}
}

class SimpleProxy implements Interface {
	private Interface proxied;
	private static int doSomethingCount = 0;
	private static int somethingElseCount = 0;
	
	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}
	
	@Override
	public void doSomething() {
		System.out.println("SimpleProxy doSomething");
		doSomethingCount++;
		proxied.doSomething();
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("SimpleProxy someThingElse " + arg);
		somethingElseCount++;
		proxied.somethingElse(arg);
	}
	
}