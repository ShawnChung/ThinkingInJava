package innerclasses;

public class Parcel8 {
	/*public Wrapping wrapping(int x) {
		return new Wrapping(x) {
			public int value() {
				return super.value() * 47;
			}
		};
	}*/
	
	public Wrapping wrapping(int x) {
		class MyWrapping extends Wrapping {

			public MyWrapping(int x) {
				super(x);
			}
			
			@Override
			public int value() {
				return super.value() * 47;
			}
		}
		
		return new MyWrapping(x);
	}
	
	public class Wrapping {
		private int i;
		public Wrapping(int x) {
			i = x;
		}
		
		public int value() {
			return i;
		}
	}
}
