package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageBase {

	

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(linkText="Change password")
	WebElement changePasswordLink ; 

	@FindBy(id = "OldPassword")
	WebElement oldPasswordTxt;

	@FindBy(id = "NewPassword2")
	WebElement newPasswordTxt;

	@FindBy(id = "ConfirmNewPassword")
	WebElement confirmPasswordTxt;

	@FindBy(css = "input.button-1.change-password-button")
	WebElement ChangePasswordBtn;

	@FindBy(xpath ="/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div")
	public WebElement resultLbl;



	public void openChangePasswordPage() 
	{
		clickButton(changePasswordLink);
	}
	public void ChangePassword(String oldpassword, String newpassword) {
		setTextElementText(oldPasswordTxt, oldpassword);
		setTextElementText(newPasswordTxt, newpassword);
		setTextElementText(confirmPasswordTxt, newpassword);
		clickButton(ChangePasswordBtn);
	}
}
