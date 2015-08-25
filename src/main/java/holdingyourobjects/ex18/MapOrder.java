package holdingyourobjects.ex18;

import holdingyourobjects.ex1.Gerbil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapOrder {
	public static void main(String[] args) {
		Map<String, Gerbil> gerbils = new HashMap<String, Gerbil>();
		gerbils.put("Tom", new Gerbil(1));
		gerbils.put("Ken", new Gerbil(4));
		gerbils.put("Zim", new Gerbil(9));
		
		System.out.println(gerbils);
		Set<String> keys = gerbils.keySet();
		System.out.println("keys: " + keys);
		TreeSet<String> sortedKeys = new TreeSet<String>(keys);
		System.out.println("sorted keys: " + sortedKeys);
		Map<String, Gerbil> linkedHashMap = new LinkedHashMap<String, Gerbil>();
		for (String key : sortedKeys) {
			linkedHashMap.put(key, gerbils.get(key));
		}
		System.out.println(linkedHashMap);
	}
}
