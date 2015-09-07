package innerclasses;

public class InheritInner extends WithInner.Inner {
	InheritInner(WithInner wi) {
		wi.super();
	}
	
	public static void main(String[] args) {
		WithInner wi = new WithInner();
		InheritInner in = new InheritInner(wi);
	}
}

class WithInner {
	class Inner {}
}
