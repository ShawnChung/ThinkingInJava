package exceptions.ex24;

public class FailingConstructor {
	private Disposable d1;
	private Disposable d2;
	
	public FailingConstructor() throws Exception {
		d1 = new Disposable();
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		try {
			FailingConstructor fc = new FailingConstructor();
			try {
				
			} finally {
				fc.dispose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dispose() {
		System.out.println("FailingConstructor dispose");
	}
}

class Disposable {
	public void dispose() {
		System.out.println("FailingConstructor dispose");
	}
}
