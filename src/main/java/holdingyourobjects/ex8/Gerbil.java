package holdingyourobjects.ex8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Gerbil {
	private int gerbilNumber;
	Gerbil(int gerbilNumber) {
		this.gerbilNumber = gerbilNumber;
	}
	
	void hop() {
		System.out.println("Gerbil " + gerbilNumber);
	}
	
	public static void main(String[] args) {
		List<Gerbil> gerbils = new ArrayList<Gerbil>();
		for (int i = 0; i < 10; i++) {
			gerbils.add(new Gerbil(i));
		}
		
		/*for (Gerbil gerbil : gerbils) {
			gerbil.hop();
		}*/
		Iterator<Gerbil> iterator = gerbils.iterator();
		while (iterator.hasNext()) {
			iterator.next().hop();
		}
	}
}
