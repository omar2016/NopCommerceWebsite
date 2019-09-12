package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegisterationpage;

public class UserRegisterationWithDDTandExcelsheet extends TestBase {

	HomePage homeObject ; 
	UserRegisterationpage registerObject ;
	LoginPage loginobject ;

	@DataProvider(name="Exceldata")
	public static Object[][] UserregisterData(){
		ExcelReader EX = new ExcelReader();
		return EX.getExcelData();
	}
	@Test(priority=1,alwaysRun=true,dataProvider="Exceldata")
	public void userCanRegisterSuccessfully(String firstName ,String lastName , String email ,String password) {
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegisterationpage(driver); 
		registerObject.userRegistration(firstName, lastName, email, password);
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
