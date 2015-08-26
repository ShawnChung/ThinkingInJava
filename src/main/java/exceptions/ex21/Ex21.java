package exceptions.ex21;

public class Ex21 {
	public Ex21() throws Exception { }
}

class Ex21_2 extends Ex21 {

	public Ex21_2() throws Exception {
		super();
	}
	
	//! will not work
	/*public Ex21_2(String s) {
		try {
			super();
		} catch (Exception e) {
			
		}
	}*/
	
}
