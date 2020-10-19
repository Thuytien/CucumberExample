package SeleniumTestExample;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class searchForAKeyword {
	
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		
		initializeChrome();
		String google = "https://www.google.com";
		goToURL(google);
		Thread.sleep(2000);
		 
		String keyword = "sephora brush";
		searchFor(keyword);
		Thread.sleep(2000);
		
		closeBrowser();
	}
	
	public static void initializeChrome() {
		System.setProperty("webdriver.chrome.driver","/Users/thuytienvo/Projects/Cucumber/CucumberJava/src/test/java/util/chromedriver");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static void goToURL(String url) {
		driver.get(url);
	}
	
	public static void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	public static void searchFor(String keyword) {
		WebElement searchField = driver.findElement(By.xpath("//input[@type='text']"));
		 searchField.clear();
		 searchField.sendKeys(keyword);
		 WebElement searchBtn = driver.findElement(By.xpath("//input[@name='btnK' and @type='submit']"));
		 searchBtn.click();
	}
}
