package typeinfo.ex1;


interface HasBatteries {}
interface Waterproof {}
interface Shoots {}
interface Fly {}

class Toy {
	public Toy() {}
	public Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots, Fly {
	public FancyToy () {
		super(1);
	}
}

public class ToyTest {
	static void printInfo(Class cc) {
		System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
		System.out.println("Simple name: " + cc.getSimpleName());
		System.out.println("Canonical name: " + cc.getCanonicalName());
	}
	
	public static void main(String[] args) {
		Class c = null;
		try {
			c = Class.forName("typeinfo.ex1.FancyToy");
		} catch (ClassNotFoundException e) {
			System.exit(1);
		}
		printInfo(c);
		for (Class cl : c.getInterfaces())
			printInfo(cl);
		Class up = c.getSuperclass();
		Object obj = null;
		try {
			obj = up.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(1);
		}
		printInfo(obj.getClass());
	}
}
