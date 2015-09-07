package innerclasses;

/**
 * A local inner class cannot have an access specifier because it isn't part of the outer
 * class, but it does have access to the final variables in the current code block and all
 * the members of the enclosing class.
 * @author Shawn
 *
 */
public class LocalInnerClass {
	private int count = 0;
	Counter getCounter(final String name) {
		class LocalCounter implements Counter {
			
			LocalCounter() {
				System.out.println("LocalCounter() ");
			}
			public int next() {
				System.out.print(name + " ");
				return count++;
			}
		
		};
		return new LocalCounter();
	}
	
	/**
	 * Since the name of the local inner class is not accessible outside the method, the only 
	 * justification for using a local inner class instead of an anonymous inner class is if you need
	 * a named constructor and/or an overloaded constructor, since an anonymous inner class can only use
	 * instance initialization.
	 * Another reason to make a local inner class rather than an anonymous inner class is if you
	 * need to make more than one object of that class.
	 */
	Counter getCounter2(final String name) {	
		return new Counter() {
			{ System.out.println("AnonymousCounter() "); }
			public int next() {
				System.out.print(name + " ");
				return count++;
			}
			
		};
	}
	
	public static void main(String[] args) {
		LocalInnerClass lic = new LocalInnerClass();
		Counter c1 = lic.getCounter("Local inner");
		Counter c2 = lic.getCounter2("Anonymous inner");
		for (int i = 0; i < 5; i++) {
			System.out.println(c1.next());
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(c2.next());
		}
	}
}

interface Counter {
	int next();
}
