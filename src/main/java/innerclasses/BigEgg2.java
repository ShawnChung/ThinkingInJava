package innerclasses;


public class BigEgg2 extends Egg2 {
	public class Yolk extends Egg2.Yolk {
		public Yolk() {
			System.out.println("BigEgg2.Yolk()");
		}
		public void f() {
			System.out.println("BigEgg2.Yolk.f()");
		}
	}
	
	public BigEgg2() {
		System.out.println("New BigEgg2()");
		insertYolk(new Yolk());
	}
	
	public static void main(String[] args) {
		new BigEgg2().g();;
	}
}

class Egg2 {
	private Yolk y;
	protected class Yolk {
		public Yolk() {
			System.out.println("Egg2.Yolk()");
		}
		public void f() {
			System.out.println("Egg2.Yolk.f()");
		}
	}
	
	public Egg2() {
		System.out.println("New Egg2()");
		y = new Yolk();
	}
	
	public void insertYolk(Yolk y) {
		this.y = y;
	}
	
	public void g() {
		y.f();
	}
}
