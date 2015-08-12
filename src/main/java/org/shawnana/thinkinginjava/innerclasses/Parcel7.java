package org.shawnana.thinkinginjava.innerclasses;

public class Parcel7 {
	/** What this strange syntax means is "Create an object of an anonymous class that's inherited
	 * from Contents."
	 */
	public Contents contents() {
		return new Contents() {
			private int i = 11;
			public int value() {
				return i;
			}
		};
	}
	
	public static void main(String[] args) {
		Parcel7 p = new Parcel7();
		Contents c = p.contents();
	}
}
