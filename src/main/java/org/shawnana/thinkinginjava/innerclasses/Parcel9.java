package org.shawnana.thinkinginjava.innerclasses;

public class Parcel9 {
	public Destination destination(final String dest) {
		return new Destination() {
			private String label = dest;
			public String readLabel() {
				return label;
			}
		};
	}
	
	public Destination destination2(final String dest) {
		class MyDestination implements Destination {
			private String label = dest;
			public String readLabel() {
				return label;
			}
			
		}
		
		MyDestination d = new MyDestination();
		return d;
	}
	
	public static void main(String[] args) {
		Parcel9 p = new Parcel9();
		Destination d = p.destination("China");
	}
}
