package operators.ex5;

public class Dog {
	private String name;
	private String says;
	public Dog(String name, String says) {
		this.name = name;
		this.says = says;
	}
	public String toString() {
		return this.name + " says '" + this.says + "'";
	}
	public static void main(String[] args) {
		Dog spot = new Dog("spot", "Ruff!");
		Dog scruffy = new Dog("scruffy", "Wurf!");
		System.out.println(spot);
		System.out.println(scruffy);
	}
}
