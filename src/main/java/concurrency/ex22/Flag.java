package concurrency.ex22;

public class Flag {
	private static volatile boolean flag = false;
	
	public static synchronized void setFlag(boolean flag1) {
		flag = flag1;
	}
	
	public static synchronized boolean getFlag() {
		return flag;
	}
}
