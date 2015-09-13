package generics;

public class GenericMethod {
	public <T, E, V> T f(T x, E e, V v) {
		System.out.println(x.getClass().getName());
		return x;
	}
	
	public static void main(String[] args) {
		GenericMethod gm = new GenericMethod();
		gm.f("", 1, 1.0);
		gm.f(1, 'a', "");
		gm.f(1.0, 1, 1.0F);
		gm.f(1.0F, 1L, true);
		gm.f('c', 2, "");
		gm.f(gm, new StringBuilder(), new Exception());
	}
}
