package exceptions;

public class StormyInning implements Storm {
	// Ok to add new exceptions for constructor, but
	// you must deal with the base constructor exceptions
	public StormyInning() throws BaseballException, RainedOut {
		
	}
	
	public StormyInning(String s) throws BaseballException, Foul {
		
	}
	
	// Regular methods must conform to base class
	//! public void walk() throws PopFoul { }
	public void walk() { }
	
	// Interface CANNOT add exceptions to
	// existing methods from the base class
	//! public void event() throws RainedOut {}
	
	// If the method doesn't already exist in
	// the base class, the exception is OK
	@Override
	public void rainHard() throws RainedOut { }

	// Overriden methods can throw inherited exceptions
	//@Override
	public void atBat() throws PopFoul { }
	
	// You can choose not to throw ANY exceptions,
	// even if the base class does
	public void event() {}
	
	public static void main(String[] args) {
		StormyInning si;
		try {
			si = new StormyInning();
			si.atBat();
		} catch (PopFoul e) {
			e.printStackTrace();
		} catch (RainedOut e) {
			e.printStackTrace();
		} catch (BaseballException e) {
			e.printStackTrace();
		}
		
		// Strike not thrown in derived version
		try {
			StormyInning i = new StormyInning();
			i.atBat();
		} catch (Strike e) {
			e.printStackTrace();
		} catch (Foul e) {
			e.printStackTrace();
		} catch (RainedOut e) {
			e.printStackTrace();
		} catch (BaseballException e) {
			e.printStackTrace();
		}
	}

}

class BaseballException extends Exception {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}
class PopFoul extends Foul {}
class StormException extends Exception {}
class RainedOut extends StormException {}
class UmpireArgument extends PopFoul {}

abstract class Inning {
	public Inning() throws BaseballException { }
	public void event() throws BaseballException {}
	public abstract void atBat() throws Strike, Foul;
	public void walk() throws UmpireArgument {}
}

interface Storm {
	public void event() throws RainedOut;
	public void rainHard() throws UmpireArgument, RainedOut;
	public void walk() throws UmpireArgument;
}


