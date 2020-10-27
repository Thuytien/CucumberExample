package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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
			 driver.get(url);
			 Thread.sleep(2000);
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
			 
			 //driver.quit();
		}
		
		@Then ("^Close browser$")
		public void closeBrowser() throws Throwable {
			driver.quit();
		}
		
		@When ("^User signup with \"([^\"]*)\" and \"([^\"]*)\"$")
		public void signupSephora(String username, String password) throws Throwable{
			WebElement signinHeader = driver.findElement(By.xpath("//button[@id='account_drop_trigger']"));
			signinHeader.click();
			WebElement createAccBtn = driver.findElement(By.xpath("//button[@data-at='create_account_menu']"));
			createAccBtn.click();
			WebElement firstName = driver.findElement(By.xpath("//input[@data-at='first_name_input' and @name='firstName']"));
			firstName.clear();
			firstName.sendKeys("test2");
			WebElement lastName = driver.findElement(By.xpath("//input[@data-at='last_name_input' and @name='lastName']"));
			lastName.clear();
			lastName.sendKeys("test");
			
			WebElement usernameField = driver.findElement(By.xpath("//input[@id='register_email' and @name='username']"));
			usernameField.clear();
			usernameField.sendKeys(username);
			WebElement passwordField = driver.findElement(By.xpath("//input[@id='password' and @name='password']"));
			passwordField.clear();
			passwordField.sendKeys(password);
			
			WebElement birthMonthSelection = driver.findElement(By.xpath("//select[@id='biRegMonth' and @name='biRegMonth']"));
			birthMonthSelection.click();
			WebElement birthMonth = driver.findElement(By.xpath("//option[@value='9']"));
			birthMonth.click();
			WebElement birthDaySelection = driver.findElement(By.xpath("//select[@id='biRegDay' and @name='biRegDay']"));
			birthDaySelection.click();
			WebElement birthDay = driver.findElement(By.xpath("//option[@value='25']"));
			birthDay.click();
			WebElement zipCode = driver.findElement(By.xpath("//input[@id='zipCode' and @name='zipCode']"));
			zipCode.clear();
			zipCode.sendKeys("90231");
			
			WebElement joinNowbtn = driver.findElement(By.xpath("//button[@data-at='join_now' and @type='submit']"));
			joinNowbtn.click();
		}
		
		@Then ("^Verify that user \"([^\"]*)\" signup successfully$")
		public void verifySignupSephora(String user) throws Throwable{
			verifyLoginSephora(user);
		}
		
		@Then ("^User logout Sephora$")
		public void logoutSephora() throws Throwable {
			WebElement signinHeader = driver.findElement(By.xpath("//button[@id='account_drop_trigger']"));
			signinHeader.click();
			WebElement signoutBtn = driver.findElement(By.xpath("//button[@data-at='sign_out_button']"));
			signoutBtn.click();
		}
		
		@When ("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
		public void loginSephora(String username, String password) throws Throwable{
			WebElement signinHeader = driver.findElement(By.xpath("//button[@id='account_drop_trigger']"));
			signinHeader.click();
			WebElement signinMenu = driver.findElement(By.xpath("//button[@data-at='sign_in_menu']"));
			signinMenu.click();
			WebElement usernameField = driver.findElement(By.xpath("//input[@data-at='signin_email' and @name='username']"));
			usernameField.clear();
			usernameField.sendKeys(username);
			WebElement passwordField = driver.findElement(By.xpath("//input[@data-at='signin_password' and @name='password']"));
			passwordField.clear();
			passwordField.sendKeys(password);
			
			WebElement signinBtn = driver.findElement(By.xpath("//button[@data-at='sign_in_button']"));
			signinBtn.click();
		}
		
		@Then ("^Verify that user \"([^\"]*)\" logged into Sephora$")
		public void verifyLoginSephora(String user) throws Throwable{
			WebElement username = driver.findElement(By.xpath("//button[@id='account_drop_trigger']//span[@class='css-1qhmto6]"));
			String name = username.getText();
			Assert.assertEquals(user, name); 
			
			WebElement greeting = driver.findElement(By.xpath("//div//strong[@data-at='person_greeting']"));
			String greet = greeting.getText();
			Assert.assertThat(greet, CoreMatchers.containsString(user));
			
		}
		
}
