package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionProxy implements InvocationHandler {
	private Object proxied;
	
	public TransactionProxy(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(method.getName().contains("needTransaction")) {
			// begin transaction
			System.out.println("begin transaction");
			Object result = null;
			try {
				result = method.invoke(proxied, args);
			} catch (Exception e) {
				// rollback transaction
				System.out.println("rollback transaction");
				throw e;
			}
			// commit transaction
			System.out.println("commit transaction");
			return result;
		} else {
			System.out.println("no transaction");
			return method.invoke(proxied, args);
		}
	}
	
	public static void main(String[] args) {
		Interface1 proxy = (Interface1) Proxy.newProxyInstance(Interface1.class.getClassLoader(), 
				new Class[] {Interface1.class}, 
				new TransactionProxy(new ActualClass()));
		proxy.noTransaction();
		proxy.needTransaction();
	}
}

interface Interface1 {
	void noTransaction();
	void needTransaction();
}

class ActualClass implements Interface1 {

	@Override
	public void noTransaction() {
		System.out.println("actual noTransaction");
	}

	@Override
	public void needTransaction() {
		System.out.println("actual needTransaction");
	}
	
}


