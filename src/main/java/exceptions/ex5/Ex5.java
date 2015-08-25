package exceptions.ex5;

public class Ex5 {
	public static void main(String[] args) {
		boolean loop = true;
		int i = 0;
		while (loop) {
			try {
				if (i < 5) {
					i ++;
					throw new Exception();
				}
				loop = false;
			} catch (Exception e) {
				loop = true;
				System.out.println("Error");
			}
			System.out.println(i);
		}
	}
}
