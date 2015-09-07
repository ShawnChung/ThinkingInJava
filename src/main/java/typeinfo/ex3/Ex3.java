package typeinfo.ex3;

class Shape {}
class Rhomboid extends Shape {}
class Circle extends Shape {}

public class Ex3 {
	public static void main(String[] args) {
		Shape s = new Rhomboid();
		if (s instanceof Rhomboid) {
			Rhomboid r = (Rhomboid)s;
		}
		if (s instanceof Circle) {
			Circle c = (Circle)s;
		}
	}
}
