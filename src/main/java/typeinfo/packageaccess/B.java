package typeinfo.packageaccess;

class B implements A {

	@Override
	public void f() {
		System.out.println("B f()");
	}
	
	public void g() {
		System.out.println("B g()");
	}

}
