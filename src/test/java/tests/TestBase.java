package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Helper;


public class TestBase  {
	WebDriver driver;
	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) 
	{
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			//  DesiredCapabilities cap = DesiredCapabilities.chrome();
			//  cap.setCapability("applicationCacheEnabled", false);
			// driver = new ChromeDriver(cap);
		}

		else if(browserName.equalsIgnoreCase("headless")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					System.getProperty("user.dir")+"/drivers/phantomjs.exe");
			String [] phantomjsArg= {"--web-security=no","--ignore-ssl-error=yes"};
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,phantomjsArg );
			driver = new PhantomJSDriver(caps);
		}
		else if(browserName.equalsIgnoreCase("chrome-headless")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--windows-size=1920,1080");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			driver = new FirefoxDriver(); 
		}

		else if (browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(); 
		}

		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.navigate().to("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();

	}



	/*public void clearCache()  {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driver.get("chrome://settings/clearBrowserData");
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();
	}*/
	@AfterMethod 
	public void screenShotOnFailure (ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("failed!");
			System.out.println("TakeScreenshot");
			Helper.captureScreenShot(driver, result.getName());
		}
	}
	/*@Test
	public  void clearBrowserCache() {
		System.out.println("Open Chrome setting");
		driver.navigate().to("chrome://settings/clearBrowserData");

		// begin identify clear data button via nested Shadow Dom elements
		// get 1st parent
		WebElement root1 =driver.findElement(By.cssSelector("settings-ui"));
		// get 1st shadowroot element
		WebElement shadowRoot1 = expandRootElement(root1);

		// get 2nd parent
		WebElement root2 = shadowRoot1.findElement(By.cssSelector("settings-main"));
		// get 2nd shadowroot element
		WebElement shadowRoot2 = expandRootElement(root2);

		// get 3rd parent
		WebElement root3 = shadowRoot2.findElement(By.cssSelector("settings-basic-page"));
		// get 3rd shadowroot element
		WebElement shadowRoot3 = expandRootElement(root3);

		// get 4th parent
		WebElement root4 = shadowRoot3.findElement(By.cssSelector("settings-section > settings-privacy-page"));
		// get 4th shadowroot element
		WebElement shadowRoot4 = expandRootElement(root4);

		// get 5th parent
		WebElement root5 = shadowRoot4.findElement(By.cssSelector("settings-clear-browsing-data-dialog"));
		// get 5th shadowroot element
		WebElement shadowRoot5 = expandRootElement(root5);

		// get 6th parent
		WebElement root6 = shadowRoot5.findElement(By.cssSelector("#clearBrowsingDataDialog"));

		// get button (finally!)
		WebElement clearDataButton = root6.findElement(By.cssSelector("#clearBrowsingDataConfirm"));
		// end identify clear data button via nested Shadow Dom elements

		clearDataButton.click(); // click that hard to reach button!
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot",element);
		return ele;
	}*/
	@AfterSuite
	public void stopDriver() 
	{
		if (driver !=null ) {
			driver.quit();
		}

	}
}
