package org.shawnana.thinkinginjava.innerclasses;

public class Parcel4 {
	private class PContents implements Contents {
		private int i = 11;
		public int value() {
			return i;
		}
		
	}
	
	private class PDestination implements Destination {
		private String label;
		private PDestination(String whereTo) {
			this.label = whereTo;
		}
		public String readLabel() {
			return label;
		}
	}
	
	public Destination destination(String s) {
		return new PDestination(s);
	}
	
	public Contents contents() {
		return new PContents();
	}
	
}
