package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.*;

public class searchSephora {
	
	    private static WebDriver driver;

		@Given("^Browse to URL \"([^\"]*)\"$")
		public void browseToURL(String url) throws Throwable{
			 System.setProperty("webdriver.chrome.driver","/Users/thuytienvo/Projects/Cucumber/CucumberJava/src/test/java/util/chromedriver");
			 driver = new ChromeDriver();
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 driver.get("https://www.google.com/");
		}
		
		@When("^Search for \"([^\"]*)\"$")
		public void searchForKeyword(String key) throws Throwable{
			 WebElement searchField = driver.findElement(By.xpath("//input[@type='text']"));
			 searchField.clear();
			 searchField.sendKeys("sephora");
			 WebElement searchBtn = driver.findElement(By.xpath("//input[@name='btnK' and @type='submit']"));
			 searchBtn.click();
			 
			 Thread.sleep(2000);
		}
		@Then("^Go to Sephora page$")
		public void goToPage() throws Throwable{
			WebElement sephoraWeb = driver.findElement(By.xpath("//a[@href='https://www.sephora.com/']"));
			 sephoraWeb.click();
			 Thread.sleep(2000);
			 
			 driver.quit();
		}
}
