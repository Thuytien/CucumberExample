package stepDefinition;

import page.Page_Sephora;

import cucumber.api.java.en.*;

public class searchSephora extends BaseDef{
	
		@When("^Search for \"([^\"]*)\"$")
		public void searchForKeyword(String key) throws Throwable{
			 Page_Sephora sephoraPage = new Page_Sephora();
			 sephoraPage.searchForKeyword(key);
		}
		
		@And("^Go to Sephora page$")
		public void goToPage() throws Throwable{
			Page_Sephora sephoraPage = new Page_Sephora();
			sephoraPage.goToSephoraFromSearchResults();
		}
		
		@When ("^User signup with \"([^\"]*)\" and \"([^\"]*)\"$")
		public void signupSephora(String username, String password) throws Throwable{
			Page_Sephora sephoraPage = new Page_Sephora();
			sephoraPage.signupToSephora(username, password);
		}
		
		@Then ("^Verify that user \"([^\"]*)\" signup successfully$")
		public void verifySignupSephora(String firstName) throws Throwable{
			verifyLoginSephora(firstName);
		}
		
		@Then ("^User logout Sephora$")
		public void logoutSephora() throws Throwable {
			Page_Sephora sephoraPage = new Page_Sephora();
			sephoraPage.logoutSephora();
		}
		
		@When ("^User login with \"([^\"]*)\" and \"([^\"]*)\"$")
		public void loginSephora(String username, String password) throws Throwable{
			Page_Sephora sephoraPage = new Page_Sephora();
			sephoraPage.loginSephora(username, password);
		}
		
		@Then ("^Verify that user \"([^\"]*)\" logged into Sephora$")
		public void verifyLoginSephora(String firstName) throws Throwable{
			Page_Sephora sephoraPage = new Page_Sephora();
			sephoraPage.verifyLoginSephora(firstName);
			
		}
		
}
