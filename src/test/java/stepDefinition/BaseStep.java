package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class BaseStep extends BaseDef {
	
	@Given("^Browse to URL \"([^\"]*)\"$")
    public void go_to(String url) throws Throwable {
        browser.browseURL(url);
    }
	
	@When("^User browses to URL \"([^\"]*)\"$")
    public void i_go_to(String url) throws Throwable {
		browser.browseURL(url);
    }
    
    @Then("^Close browser$")
    public void close_browser() throws Throwable {
    	browser.close();
    }
	
}
