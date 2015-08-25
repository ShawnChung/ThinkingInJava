package holdingyourobjects.ex7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FillList {
	private String name;
	public FillList(String name) {
		this.name = name;
	}
	
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		if (!(o instanceof FillList))
			return false;
		FillList fillList = (FillList) o;
		return this.name.equals(fillList.name);
	}
	
	public String toString() {
		return this.name;
	}
	
	public static void main(String[] args) {
		FillList[] fillList = {
				new FillList("A"),
				new FillList("R"),
				new FillList("G"),
				new FillList("C"),
				new FillList("K"),
				new FillList("O")
		};
		List<FillList> list = Arrays.asList(fillList);
		System.out.println(list);
		List<FillList> sub = list.subList(2,  4);
		System.out.println(sub);
		list.removeAll(sub); //! error
		System.out.println(list);
	}
}
