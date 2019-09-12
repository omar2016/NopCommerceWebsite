package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.LoadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegisterationpage;


public class UserRegisterationWithDDTandDataProperties extends TestBase {


	HomePage homeObject ; 
	UserRegisterationpage registerObject ;
	LoginPage loginobject ;
	String firstname = LoadProperties.userdata.getProperty("firstname");
	String lastname = LoadProperties.userdata.getProperty("lastname");
	String email = LoadProperties.userdata.getProperty("email");
	String password = LoadProperties.userdata.getProperty("password");

		@Test(priority=1,alwaysRun=true)
		public void userCanRegisterSuccessfully() {
			homeObject = new HomePage(driver); 
			homeObject.openRegistrationPage();
			registerObject = new UserRegisterationpage(driver); 
		
				registerObject.userRegistration(firstname,lastname, email, password);
				Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
			}
			
		@Test (dependsOnMethods= {"userCanRegisterSuccessfully"})
		public void registeredUserCanLogout () {
			registerObject.userLogout();
		}
		@Test (dependsOnMethods= {"registeredUserCanLogout"})
		public void registeredUserCanLogin ()  {
			homeObject.OpenLoginpage();
			LoginPage loginobject = new LoginPage(driver);
			loginobject.userLogin(email,password);
			Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}

		

	}
	
}
