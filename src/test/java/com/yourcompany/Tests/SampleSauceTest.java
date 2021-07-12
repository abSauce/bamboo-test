import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;

public class SampleSauceTest {
	protected static WebDriver driver;
	public static final String sauceURL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
  	public static final String UserName = System.getenv("SAUCE_USERNAME");
  	public static final String Password = System.getenv("SAUCE_ACCESS_KEY");

	// @Parameters ( {"sauceUserName", "sauceAccessKey" })
	@BeforeMethod 
	public static void openbrowser(Method method) throws MalformedURLException {
		String methodName = method.getName();
	    DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("version", "latest");
	    caps.setCapability("username", UserName);
	    caps.setCapability("accessKey", Password);
	    driver = new RemoteWebDriver(new URL(sauceURL), caps);    	
	}

	private static void printSessionId() {
   		String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s",
    	(((RemoteWebDriver) driver).getSessionId()).toString(), "some job name");
    	System.out.println(message);
	}

	@Test
	public static void Test() throws InterruptedException {
		driver.get("https://saucedemo.com");
		printSessionId();
	}

	@AfterMethod
	public static void closeBrowser() {
		//driver.close();
		driver.quit();
	}

}