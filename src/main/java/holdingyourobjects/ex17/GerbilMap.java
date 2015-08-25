package holdingyourobjects.ex17;

import holdingyourobjects.ex1.Gerbil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GerbilMap {
	public static void main(String[] args) {
		Map<String, Gerbil> gerbils = new HashMap<String, Gerbil>();
		gerbils.put("Tom", new Gerbil(1));
		gerbils.put("Ken", new Gerbil(4));
		gerbils.put("Kim", new Gerbil(9));
		Iterator iterator = gerbils.keySet().iterator();
		while (iterator.hasNext()) {
			gerbils.get(iterator.next()).hop();
		}
	}
}
