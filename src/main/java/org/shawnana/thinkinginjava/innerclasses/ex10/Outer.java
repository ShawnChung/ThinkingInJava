package org.shawnana.thinkinginjava.innerclasses.ex10;

import org.shawnana.thinkinginjava.innerclasses.ex9.Interface;

public class Outer {
	public Interface getInterface(boolean b) {
		if (b) {
			class Inner implements Interface {

				public String getValue() {
					return "Inner";
				}
				
			}
			return new Inner();
		}
		return null;
	}
}
