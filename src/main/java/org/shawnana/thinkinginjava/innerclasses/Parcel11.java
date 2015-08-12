package org.shawnana.thinkinginjava.innerclasses;

public class Parcel11 {
	private static class ParcelContents implements Contents {

		public int value() {
			return 0;
		}
		
	}
	
	protected static class ParcelDestination implements Destination {
		private String label;
		private ParcelDestination(String whereTo) {
			this.label = whereTo;
		}
		public String readLabel() {
			return label;
		}
		public static void f() { }
		static int x = 10;
		// Nested classes can contain other static elements.
		static class AnotherLevel {
			public static void f() {}
			static int x = 10;
		}
	}
	
	public static Destination destination(String s) {
		return new ParcelDestination(s);
	}
	
	public static Contents contents() {
		return new ParcelContents();
	}
	
	public static void main(String[] args) {
		Contents c = contents();
		Destination d = destination("China");
	}
}
