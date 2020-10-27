@skipsephora
Feature: Search for Sephora web

  Scenario: Search for Sephora web and go to the page
    Given Browse to URL "https://www.google.com/"
    When Search for "sephora" 
    And Go to Sephora page
    Then Close browser
#----------------------------------------------------------------------

	Scenario: Signup to Sephora
		Given Browse to URL "https://www.sephora.com/"
		When User signup with "rebustest2@gmail.com" and "123456"
		Then Verify that user "test2" signup successfully
		Then User logout Sephora
		Then Close browser
#----------------------------------------------------------------------

	Scenario: Login to Sephora
		Given Browse to URL "https://www.sephora.com/"
		When User login with "rebustest1@gmail.com" and "123456"
		Then Verify that user "test" logged into Sephora
		Then Close browser