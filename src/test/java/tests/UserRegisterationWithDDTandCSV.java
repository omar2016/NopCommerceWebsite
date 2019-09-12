package tests;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegisterationpage;

public class UserRegisterationWithDDTandCSV extends TestBase{

	HomePage homeObject ; 
	UserRegisterationpage registerObject ; 
	CSVReader reader ; 

	@Test(priority=1,alwaysRun=true)
	public void userCanRegisterSuccessfully() throws FileNotFoundException  {

		//get the path of the csv file
		String csvfile = System.getProperty("user.dir")+"/src/test/java/data/userdata.csv";

		reader = new CSVReader(new FileReader(csvfile)) ;

		String [] csvcell;

		try {
			while ((csvcell=reader.readNext()) != null) {
				String firstname = csvcell[0];
				String lastname = csvcell[1];
				String email = csvcell[2];
				String password = csvcell[3];

				homeObject = new HomePage(driver); 
				homeObject.openRegistrationPage();
				registerObject = new UserRegisterationpage(driver); 
				registerObject.userRegistration(firstname, lastname, email, password);
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}






