package typeinfo.ex7;

class Candy {
	static {
		System.out.println("Loading Candy");
	}
}

class Gum {
	static {
		System.out.println("Loading Gum");
	}
}

class Cookie {
	static {
		System.out.println("Loading Cookie");
	}
}

public class SweetShop {
	public static void main(String[] args) {
		System.out.println("inside main");
		if (args.length < 1) System.exit(1);
		try {
			Class.forName(args[0]);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
