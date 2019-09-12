package tests;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class OpenIncognito {
	public WebDriver driver;
	@Test
	public void startDriver () {
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--incognito");
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	driver=new ChromeDriver(capabilities);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	driver.navigate().to("http://demo.nopcommerce.com/");
	}
}
