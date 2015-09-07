package typeinfo.ex10;

public class Ex10 {
	public static void main(String[] args) {
		char[] cs = new char[] {'s', 'h', '6'};
		Class c = cs.getClass();
		System.out.println(c.getCanonicalName());
	}
}
