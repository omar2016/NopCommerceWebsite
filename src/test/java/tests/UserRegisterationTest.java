package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegisterationpage;

public class UserRegisterationTest extends TestBase {

	HomePage homeObject ; 
	UserRegisterationpage registerObject ; 
	
	
	@Test(priority=1,alwaysRun=true)
	public void userCanRegisterSuccessfully() {
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegisterationpage(driver); 
		registerObject.userRegistration("omar", "elnabrawy", "testomar44ffaffe4@gmail.com", "123456");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	@Test (dependsOnMethods= {"userCanRegisterSuccessfully"})
	public void registeredUserCanLogout () {
		registerObject.userLogout();
	}
	@Test (dependsOnMethods= {"registeredUserCanLogout"})
	public void registeredUserCanLogin () throws InterruptedException {
		homeObject.OpenLoginpage();
		LoginPage loginobject = new LoginPage(driver);
		loginobject.userLogin("testomar44ffaffe4@gmail.com", "123456");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
		Thread.sleep(3000);
	}

}

