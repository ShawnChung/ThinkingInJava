package generics.ex2;

public class Ex2<T> {
	private T first;
	private T second;
	private T third;
	
	public Ex2(T first, T second, T third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public static void main(String[] args) {
		Ex2<String> ex2String = new Ex2<String>("first", "second", "third"); 
		Ex2<Integer> ex2Integer = new Ex2<Integer>(1, 2, 3);
		System.out.println(ex2String);
		System.out.println(ex2Integer);
	}
	
	public String toString() {
		return this.first + " " + this.second + " " + this.third;
	}
}
