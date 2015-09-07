package typeinfo.ex8;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Ex8 {
	public static void main(String[] args) {
		printSuperClass(new ArrayList().getClass());
		printDeclaredFields(new ArrayList().getClass());
	}
	
	public static void printSuperClass(Class c) {
		if (c.getSuperclass() != null)
			printSuperClass(c.getSuperclass());
		System.out.println(c.getCanonicalName());
	}
	
	public static void printDeclaredFields(Class c) {
		for (Field f : c.getDeclaredFields()) {
			System.out.println("field type: " + f.getType() + ", field name: " + f.getName());
		}
	}
}

class Ex8ex extends Ex8 { }