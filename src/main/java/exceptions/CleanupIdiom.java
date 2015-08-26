package exceptions;

public class CleanupIdiom {
	public static void main(String[] args) {
		// Section 1:
		NeedsCleanup nc1 = new NeedsCleanup();
		try {
			// 
		} finally {
			nc1.dispose();
		}
		
		// Section 2:
		// If construction cannot fail you can group objects
		NeedsCleanup nc2 = new NeedsCleanup();
		NeedsCleanup nc3 = new NeedsCleanup();
		try {
			// ...
		} finally {
			nc3.dispose();  // Reverse order of construction
			nc2.dispose();
		}
		
		// Section 3:
		// If construction can fail you must guard each one
		try {
			NeedsCleanup2 nc4 = new NeedsCleanup2();
			try {
				NeedsCleanup2 nc5 = new NeedsCleanup2();
				try {
					
				} finally {
					nc5.dispose();
				}
			} catch (ConstructionException e) {
				System.out.println(e);
			} finally {
				nc4.dispose();
			}
		} catch (ConstructionException e) {
			System.out.println(e);
		}
	}
}

class NeedsCleanup {
	private static long counter = 1;
	private long id = counter++;
	public void dispose() {
		System.out.println("NeedsCleanup " + id + " disposed");
	}
}

class NeedsCleanup2 extends NeedsCleanup {
	public NeedsCleanup2() throws ConstructionException {}
}

class ConstructionException extends Exception { }
