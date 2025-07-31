Feature: Login functionality

  @smoke
  Scenario: Valid login with correct credentials
    Given user launches the application
    When user enters valid username and password
    Then user should be navigated to the homepage
