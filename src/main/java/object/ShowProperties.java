package object;

import java.util.Map.Entry;

public class ShowProperties {
	public static void main(String[] args) {
		// System.getProperties().list(System.out);
		for (Entry<String, String> entry : System.getenv().entrySet()) {
			System.out.println(entry);
		}
	}
}
