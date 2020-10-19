package handler;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
	
	public WebDriver driver;
	
	public Browser() {
		
	}

	public void initializeChromeBrowser() {
		 System.setProperty("webdriver.chrome.driver","/Users/thuytienvo/Projects/Cucumber/CucumberJava/src/test/java/util/chromedriver");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void clearCurrentDomainCookie() {
		// Only delete cookies of the current Domain on browser
		driver.manage().deleteAllCookies();
	}

	public void browseURL(String siteURL) {
		initializeChromeBrowser();
		driver.navigate().to(siteURL);
		Test.waitPageLoad();
	}
	
	public void close() {
		boolean driverHasQuited = driver.toString().contains("(null)");
		if (driverHasQuited) {
			return;
		}
		driver.close();
		driver.quit();
	}
	
	public void verifyURL(String expectedURL) {
		String actualURL = driver.getCurrentUrl();
		Assert.assertTrue("Expecting URL: | " + expectedURL + " | but get: |" + actualURL + "|",
				expectedURL.equals(actualURL));
	}
}
