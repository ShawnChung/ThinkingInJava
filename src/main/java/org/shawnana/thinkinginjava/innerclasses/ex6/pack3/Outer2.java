package org.shawnana.thinkinginjava.innerclasses.ex6.pack3;

import org.shawnana.thinkinginjava.innerclasses.ex6.pack1.Interface1;
import org.shawnana.thinkinginjava.innerclasses.ex6.pack2.Outer;

public class Outer2 extends Outer {
	public Interface1 inner() {
		return new Inner();
	}
}
