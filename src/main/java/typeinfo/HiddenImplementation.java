package typeinfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import typeinfo.packageaccess.A;
import typeinfo.packageaccess.HiddenC;

public class HiddenImplementation {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		A a = HiddenC.makeA();
		a.f();
		// Reflection still allows us to call g()
		callHiddenMethod(a, "g");
		callHiddenMethod(a, "u");
		callHiddenMethod(a, "v");
		callHiddenMethod(a, "w");
	}
	
	static void callHiddenMethod(Object o, String methodName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = o.getClass().getDeclaredMethod(methodName);
		m.setAccessible(true);
		m.invoke(o);
	}
}
