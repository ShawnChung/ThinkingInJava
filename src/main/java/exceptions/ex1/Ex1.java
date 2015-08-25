package exceptions.ex1;

public class Ex1 {
	public static void main(String[] args) {
		try {
			throw new Exception("Error");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("We are here!");
		}
	}
}
