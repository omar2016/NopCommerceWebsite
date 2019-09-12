package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.MyAccountPage;
import pages.UserRegisterationpage;

public class MyAccountTests extends TestBase{
		
	HomePage homeObject ; 
	UserRegisterationpage registerObject ;
	MyAccountPage Myaccountobject ;
	String firstname = "omar";
	String lastname = "elnabrawy" ; 
	String email ="testomar1244565@gmail.com";
	String password =  "123456"; 
	String newpassword = "1234567";
	String oldpassword = "123456" ;
	
	@Test(priority=1,alwaysRun=true)
	public void userCanRegisterSuccessfully() {
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegisterationpage(driver); 
		registerObject.userRegistration(firstname, lastname, email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.openMyAccountPage();
	}
	@Test(priority=2)
	public void registeredUserCanChangePassword ( ) throws InterruptedException {
		Myaccountobject = new MyAccountPage(driver);
		Myaccountobject.openChangePasswordPage();
		Myaccountobject.ChangePassword(oldpassword, newpassword);
		Assert.assertTrue(Myaccountobject.resultLbl.getText().contains("Password was changed"));
		Thread.sleep(3000);
	}
}
