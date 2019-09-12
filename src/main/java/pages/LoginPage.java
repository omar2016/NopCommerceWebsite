package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(id = "Email")
	WebElement emailTxT ;
	
	@FindBy(id = "Password")
	WebElement passwordTxT ;

	@FindBy(id = "RememberMe")
	WebElement rememberMeCheck;
	
	@FindBy(xpath = "//*[@class='button-1 login-button']")
	WebElement loginBtn ;
	
	@FindBy(xpath = "/html/body/div[6]/div[3]/div/div/div/div/div[2]/div[1]/h2")
	public WebElement successloginAssertion;

	
	public void userLogin (String email , String password ) {
		
		setTextElementText(emailTxT, email);
		setTextElementText(passwordTxT,  password);
		clickButton(rememberMeCheck);
		clickButton(loginBtn);
	}
	
}
	
	