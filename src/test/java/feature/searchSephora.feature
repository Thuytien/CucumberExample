@sephora
Feature: Search for Sephora web

  Scenario: Search for Sephora web and go to the page
    Given Browse to URL "https://www.google.com/"
    When Search for "sephora"
    Then Go to Sephora page
