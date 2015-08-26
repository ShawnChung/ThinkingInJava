package exceptions.ex22;

public class FailingConstructor {
	public FailingConstructor() throws Exception {
		throw new Exception();
	}
	
	public static void main(String[] args) {
		try {
			FailingConstructor fc = new FailingConstructor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

