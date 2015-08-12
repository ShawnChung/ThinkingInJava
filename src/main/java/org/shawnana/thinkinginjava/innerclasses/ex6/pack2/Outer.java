package org.shawnana.thinkinginjava.innerclasses.ex6.pack2;

import org.shawnana.thinkinginjava.innerclasses.ex6.pack1.Interface1;

public class Outer {
	protected class Inner implements Interface1 {
		private String detail = "detail";
		
		public Inner() {
		}

		public String showDetail() {
			return detail;
		}
		
	}
}
