package typeinfo;

public class Shapes {
	public static void rotate(Shape shape) {
		if (shape instanceof Circle) return;
		System.out.println("Rotate shape " + shape.getClass().getName());
	}
	
	public static void main(String[] args) {
		rotate(new Shape());
		rotate(new Circle());
		rotate(new Rhomboid());
	}
}

class Shape {}
class Rhomboid extends Shape {}
class Circle extends Shape {}
