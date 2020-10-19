package page;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import handler.Test;

public class Page_Sephora {

	private final String searchTextXpath = "//input[@type='text']";
	private final String searchBtnXpath = "//input[@name='btnK' and @type='submit']";
	private final String signinHeaderXpath = "//button[@id='account_drop_trigger']";
	private final String signoutBtnXpath = "//button[@data-at='sign_out_button']";
	private final String createAccBtnXpath = "//button[@data-at='create_account_menu']";

	private final String login_signinBtnXpath = "//button[@data-at='sign_in_button']";
	private final String signinMenuXpath = "//button[@data-at='sign_in_menu']";
	private final String login_passwordXpath = "//input[@data-at='signin_password' and @name='password']";
	private final String login_usernameXpath = "//input[@data-at='signin_email' and @name='username']";
	
	// Signup Form
	private final String firstNameXpath = "//input[@data-at='first_name_input' and @name='firstName']";
	private final String lastNameXpath = "//input[@data-at='last_name_input' and @name='lastName']";
	private final String signup_usernameXpath = "//input[@id='register_email' and @name='username']";
	private final String signup_passwordXpath = "//input[@id='password' and @name='password']";
	private final String birthMonthSelectionXpath = "//select[@id='biRegMonth' and @name='biRegMonth']";
	private final String birthMonthXpath = "//option[@value='9']";
	private final String birthDaySelectionXpath = "//select[@id='biRegDay' and @name='biRegDay']";
	private final String birthDayXpath = "//option[@value='25']";
	private final String zipCodeXpath = "//input[@id='zipCode' and @name='zipCode']";
	private final String signup_joinNowbtnXpath = "//button[@data-at='join_now' and @type='submit']";
	
	private final String usernameXpath = "//button[@id='account_drop_trigger']//span[@class='css-1qhmto6]";
	private final String greetingXpath = "//div//strong[@data-at='person_greeting']";
	
	
	//------------------------------------------------------------------------------------------------------
	
	public void searchForKeyword(String keyword) {
		Test.clickThenTypeOnElement(searchTextXpath, keyword);
		Test.clickElement(searchBtnXpath);
	}
	
	public void goToSephoraFromSearchResults() {
		String sephoraURL = "//a[@href='https://www.sephora.com/']";
		Test.clickElement(sephoraURL);
	}
	
	public void signupToSephora(String username, String password) {
		
		Test.clickElement(signinHeaderXpath);
		Test.clickElement(createAccBtnXpath);
		
		Test.clearThenTextOnElement(firstNameXpath, "test2");
		Test.clearThenTextOnElement(lastNameXpath, "test");
		
		Test.clearThenTextOnElement(signup_usernameXpath, username);
		Test.clearThenTextOnElement(signup_passwordXpath, password);
		
		Test.clickElement(birthMonthSelectionXpath);
		Test.clickElement(birthMonthXpath);
		Test.clickElement(birthDaySelectionXpath);
		Test.clickElement(birthDayXpath);
		
		Test.clearThenTextOnElement(zipCodeXpath, "90231");
		
		Test.clickElement(signup_joinNowbtnXpath);
	}
	
	public void verifyLoginSephora(String firstName) {
		Test.waitPageLoad();
		Test.waitElement(usernameXpath);
		String name = Test.getElementText(usernameXpath);
		Assert.assertEquals(firstName, name); 
		
		String greetText = Test.getElementText(greetingXpath);
		Test.checkTextContainsOrNot(greetText, firstName, true);;
		
	}
	
	public void logoutSephora() {
		Test.clickElement(signinHeaderXpath);
		Test.clickElement(signoutBtnXpath);
	}
	
	public void loginSephora(String username, String password) {
		Test.clickElement(signinHeaderXpath);
		Test.clickElement(signinMenuXpath);
		
		Test.clearThenTextOnElement(login_usernameXpath, username);
		Test.clearThenTextOnElement(login_passwordXpath, password);
		
		Test.clickElement(login_signinBtnXpath);
		Test.waitPageLoad();
	}
	
}
