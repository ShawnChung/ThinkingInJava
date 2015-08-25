package holdingyourobjects.ex24;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class Ex24 {
	public static void main(String[] args) {
		Map<String, Company> companies = new LinkedHashMap<String, Company>();
		companies.put("Microsoft", new Company("Microsoft"));
		companies.put("Google", new Company("Google"));
		companies.put("Apple", new Company("Apple"));
		System.out.println(companies);
		TreeSet<String> companyNames = new TreeSet<String>(companies.keySet());
		Map<String, Company> newCompanies = new LinkedHashMap<String, Company>();
		for (String name : companyNames) {
			newCompanies.put(name,companies.get(name));
		}
		System.out.println(newCompanies);
	}
}

class Company {
	private String name;
	public Company(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
