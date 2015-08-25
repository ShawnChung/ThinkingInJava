package exceptions.ex18;

public class LostMessage {
	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	
	void dispose() throws SoHumException {
		throw new SoHumException();
	}
	
	public static void main(String[] args) {
		try {
			LostMessage lm = new LostMessage();
			try {
				lm.f();
			} finally {
				lm.dispose();
				/*try {
					lm.dispose();
				} finally {
					throw new MoreTrivialException();
				}*/
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}

class VeryImportantException extends Exception {
	public String toString() {
		return "A very Important Exception";
	}
}

class SoHumException extends Exception {
	public String toString() {
		return "A trivial Exception";
	}
}

class MoreTrivialException extends Exception {
	public String toString() {
		return "A more trivial Exception";
	}
}
