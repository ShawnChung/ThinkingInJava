package typeinfo.ex20;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassDescription {
	private static final String usage = "usage : ClassDescription package.classname";
	private String s;
	public String ss;
	private String getString() {
		return "";
	}
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(usage);
			System.exit(0);
		}
		
		try {
			Class<?> clazz = Class.forName(args[0]);
			System.out.println("CanonicalName: " + clazz.getCanonicalName());
			System.out.println("Name: " + clazz.getName());
			System.out.println("Package: " + clazz.getPackage());
			System.out.println("SimpleName: " + clazz.getSimpleName());
			System.out.println("SuperClass: " + clazz.getSuperclass());
			Class[] cs = clazz.getClasses();
			if (cs.length != 0) {
				System.out.println("Classes: ");
				for (Class c : cs) {
					System.out.println("\t" + c.getCanonicalName());
				}
			}
			System.out.println("Constructors: ");
			Constructor[] ctors = clazz.getConstructors();
			for (Constructor ctor : ctors) {
				System.out.print("\t" + ctor.getName() + "(");
				for (int i = 0; i < ctor.getParameterTypes().length ; i++) {
					if (i != ctor.getParameterTypes().length - 1)
						System.out.print(ctor.getParameterTypes()[i].getSimpleName() + ", ");
					else {
						System.out.print(ctor.getParameterTypes()[i].getSimpleName());
					}
				}
				System.out.println(")");
			}
			System.out.println("DeclaredConstructors: ");
			Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
			for (Constructor ctor : declaredConstructors) {
				System.out.print("\t" + ctor.getName() + "(");
				for (int i = 0; i < ctor.getParameterTypes().length ; i++) {
					if (i != ctor.getParameterTypes().length - 1)
						System.out.print(ctor.getParameterTypes()[i].getSimpleName() + ", ");
					else {
						System.out.print(ctor.getParameterTypes()[i].getSimpleName());
					}
				}
				System.out.println(")");
			}
			Annotation[] annotations = clazz.getAnnotations();
			if (annotations.length != 0) {
				System.out.println("Anonotations: ");
				for (Annotation annotation : annotations) {
					System.out.println("\t" + annotation.toString());
				}
			}
			Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
			if (declaredAnnotations.length != 0) {
				System.out.println("DeclaredAnnotations:");
				for (Annotation annotation : declaredAnnotations) {
					System.out.println("\t" + annotation.toString());
				}
			}
			Class[] declaredClasses = clazz.getDeclaredClasses();
			if (declaredClasses.length != 0) {
				System.out.println("DeclaredClasses: ");
				for (Class c : declaredClasses) {
					System.out.println("\t" + c.getCanonicalName());
				}
			}
			
			Field[] fields = clazz.getDeclaredFields();
			if (fields.length != 0) {
				System.out.println("DeclaredFields: ");
				for (Field field : fields) {
					System.out.println("\tfieldName: " + field.getName() + ",\tfieldType: " + field.getType());
				}
			}
			
			fields = clazz.getFields();
			if (fields.length != 0) {
				System.out.println("Fields: ");
				for (Field field : fields) {
					System.out.println("\tfieldName: " + field.getName() + ",\tfieldType: " + field.getType());
				}
			}
			
			Method[] methods = clazz.getDeclaredMethods();
			if (methods.length != 0) {
				System.out.println("DeclaredMethods:");
				
				for (Method method : methods) {
					System.out.print("\t" + method.getName() + "(");
					for (int i = 0; i < method.getParameterTypes().length ; i++) {
						if (i != method.getParameterTypes().length - 1)
							System.out.print(method.getParameterTypes()[i].getSimpleName() + ", ");
						else {
							System.out.print(method.getParameterTypes()[i].getSimpleName());
						}
					}
					System.out.println(")");
				}
			}
			
			methods = clazz.getMethods();
			if (methods.length != 0) {
				System.out.println("Methods:");
				for (Method method : methods) {
					System.out.print("\t" + method.getName() + "(");
					for (int i = 0; i < method.getParameterTypes().length ; i++) {
						if (i != method.getParameterTypes().length - 1)
							System.out.print(method.getParameterTypes()[i].getSimpleName() + ", ");
						else {
							System.out.print(method.getParameterTypes()[i].getSimpleName());
						}
					}
					System.out.println(")");
				}
			}
			
			System.out.println("IsAnnotation: " + clazz.isAnnotation());
			System.out.println("IsArray: " + clazz.isArray());
			System.out.println("IsEnum: " + clazz.isEnum());
			System.out.println("IsInterface: " + clazz.isInterface());
			System.out.println("IsLocalClass: " + clazz.isLocalClass());
			System.out.println("IsMemberClass: " + clazz.isMemberClass());
			System.out.println("IsPrimitive: " + clazz.isPrimitive());
			System.out.println("IsSynthetic: " + clazz.isSynthetic());
			System.out.println("IsAnonymousClass: " + clazz.isAnonymousClass());
			if(clazz.isAnonymousClass()) {
				System.out.println("Enclosing Class: " + clazz.getEnclosingClass());
				System.out.println("Enclosing Constructor: " + clazz.getEnclosingConstructor());
				System.out.println("Enclosing Method: " + clazz.getEnclosingMethod());
			}
		} catch (ClassNotFoundException e) {
			System.out.println("can't not find class " + args[1]);
		}
	}
}
