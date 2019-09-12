package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest  extends TestBase{

	HomePage HomeObject ;
	LoginPage loginobject ; 
	
	@Test(priority=1,alwaysRun=true)
	public void userCanLoginSuccessfully() throws InterruptedException {
		HomeObject = new HomePage(driver);
		HomeObject.OpenLoginpage();
		loginobject = new LoginPage(driver);
		loginobject.userLogin("testomar44ffffe4@gmail.com", "123456");
		Assert.assertTrue(loginobject.successloginAssertion.getText().contains("Welcome to our store"));
		Thread.sleep(3000);
	}
}
