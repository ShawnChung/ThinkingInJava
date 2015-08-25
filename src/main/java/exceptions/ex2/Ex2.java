package exceptions.ex2;

public class Ex2 {
	public static void main(String[] args) {
		try {
			Ex2 ex2 = null;
			ex2.f();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void f() {
	}
}
