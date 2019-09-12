package tests;


import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bsh.ParseException;
import data.JsonReaderWithdarray;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegisterationpage;

public class UserRegisterationWithDDTandJson extends TestBase{

	
	HomePage homeObject ; 
	UserRegisterationpage registerObject ; 
	
	@DataProvider(name="registeration data")
	public Object[][] UserregisterData() throws bsh.ParseException, IOException, ParseException, Exception  {
		return JsonReaderWithdarray.getJsonData("registeration Data",3,4);
	}
	@Test(dataProvider ="registeration data")
	public void userCanRegisterSuccessfully(String firstname,String lastname,String email,String password) {
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegisterationpage(driver); 
		registerObject.userRegistration(firstname, lastname,email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.userLogout();
		homeObject.OpenLoginpage();
		LoginPage loginobject = new LoginPage(driver);
		loginobject.userLogin(email,password);
		registerObject.userLogout();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}

