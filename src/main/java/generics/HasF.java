package generics;

public class HasF {
	public static void main(String[] args) {
		HasF hf = new HasF();
		Manipulator<HasF> manipulator = new Manipulator<HasF>(hf);
		manipulator.manipulate();
	}
	
	public void f() {
		
	}
}

class Manipulator<T> {
	private T obj;
	public Manipulator(T x) {
		this.obj = x;
	}
	public void manipulate() {
		//! obj.f(); // won't compile
	}
}

class Manipulator2<T extends HasF> {
	private T obj;
	public Manipulator2(T x) {
		this.obj = x;
	}
	public void manipulate() {
		obj.f();
	}
}
