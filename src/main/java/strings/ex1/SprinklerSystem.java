package strings.ex1;

class WaterSource {
	private String s;

	WaterSource() {
		System.out.println("WaterSource()");
		s = "Constructed";
	}

	public String toString() {
		return s;
	}
}

public class SprinklerSystem {
	private String valve1, valve2, valve3, valve4;
	private WaterSource source = new WaterSource();
	private int i;
	private float f;

	public String toString() {
		return "valve1 = " + valve1 + " " + "valve2 = " + valve2 + " "
				+ "valve3 = " + valve3 + " " + "valve4 = " + valve4 + "\n"
				+ "i = " + i + " " + "f = " + f + " " + "source = " + source;
	}
	
	// use javap -c SprinklerSystem
	public String toString2() {
		return new StringBuilder().append("valve1 = ").append(valve1).append(" ").append("valve2 = ").append(valve2).append(" ")
				.append("valve3 = ").append(valve3).append(" ").append("valve4 = ").append(valve4).append("\n")
				.append("i = ").append(i).append(" ").append("f = ").append(f).append(" ").append("source = ").append(source).toString();
	}

	public static void main(String[] args) {
		SprinklerSystem sprinklers = new SprinklerSystem();
		System.out.println(sprinklers);
	}
}