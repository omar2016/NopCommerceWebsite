package data;

import com.github.javafaker.Faker;

public class FakerData {

	
	Faker fakedata = new Faker();
	public String firstName () {
	String firstname =fakedata.name().firstName();
	return firstname;
	}
	public String lastName () {
		String lastname =fakedata.name().lastName();
		return lastname;
	}
	public String email () {
		String email =fakedata.internet().emailAddress();
		return email;
	}
	public String password () {
		String password =fakedata.number().digits(8).toString();
		return password;
	}

}
