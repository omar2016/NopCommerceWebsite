package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegisterationpage;
import data.FakerData;
public class UserRegisterationWithDDTandDataFaker extends TestBase {

	HomePage homeObject ; 
	UserRegisterationpage registerObject ;
	LoginPage loginobject ;
	
	FakerData Fakedata =new FakerData();

	@Test(priority=1,alwaysRun=true)
	public void userCanRegisterSuccessfully() {
	   
	   for (int i = 0; i < 2; i++) {
		
	    String email = Fakedata.email();
	    String password =Fakedata.password();
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegisterationpage(driver); 
		registerObject.userRegistration( Fakedata.firstName (), Fakedata.lastName (),email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.userLogout();
		homeObject.OpenLoginpage();
		LoginPage loginobject = new LoginPage(driver);
		loginobject.userLogin(email,password);
		registerObject.userLogout();
	   }
try {
	Thread.sleep(1000);
} catch (Exception e) {
	e.printStackTrace();
}
	}

	
}
