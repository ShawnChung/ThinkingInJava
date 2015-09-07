 package innerclasses;

public class Implementation1 implements Service {
	private Implementation1() {}
	
	public void method1() {
		System.out.println("Implementation1 method1");
	}

	public void method2() {
		System.out.println("Implementation1 method2");
	}
	
	public static ServiceFactory factory() {
		return new ServiceFactory() {

			public Service getService() {
				return new Implementation1();
			}
		};
	}
}
