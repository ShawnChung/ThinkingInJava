package org.shawnana.thinkinginjava.innerclasses.ex18;

public class Outer {
	private static class StaticInner {
		private class InnerInner {
			
		}
	}
	
	private class Inner {
		private class InnerInner {
			
		}
	}
	
	public static void main(String[] args) {
		StaticInner in = new StaticInner();
		StaticInner.InnerInner inin = in.new InnerInner();
		Inner inner = new Outer().new Inner();
		Inner.InnerInner ininin = inner.new InnerInner();
	}
}
