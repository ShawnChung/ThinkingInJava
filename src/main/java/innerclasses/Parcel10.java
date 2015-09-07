package innerclasses;

public class Parcel10 {
	public Destination destination(final String whereTo, final int price) {
		return new Destination() {
			private int cost;
			{
				cost = Math.round(price);
				if (cost > 10) {
					System.out.println("Over budget!");
				}
			}
			private String label = whereTo;
			public String readLabel() {
				return label;
			}
			
		};
	}
	
	public static void main(String[] args) {
		Parcel10 p = new Parcel10();
		p.destination("China", 12);
	}
}
