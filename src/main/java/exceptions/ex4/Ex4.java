package exceptions.ex4;

public class Ex4 extends Exception {
	private String msg;
	public Ex4(String msg) {
		this.msg = msg;
	}
	public String toString() {
		return msg;
	}
	
	public static void main(String[] args) {
		try {
			throw new Ex4("Error Msg");
		} catch (Ex4 e) {
			System.out.println(e);
		}
	}
}
