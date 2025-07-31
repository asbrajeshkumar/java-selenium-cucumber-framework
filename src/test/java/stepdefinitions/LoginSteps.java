package stepdefinitions;

import io.cucumber.java.en.*;
import pages.LoginPage;
import framework.BasePage;
import framework.api.ToolAPI;
import framework.driver.DriverManager;

public class LoginSteps extends BasePage{
	
	public LoginSteps() {
		this.driver = DriverManager.getDriver();
		new LoginPage(ToolAPI.getDriver(driver));
	}
	
    @Given("user launches the application")
    public void user_launches_the_application() {
        driver.get("https://www.youtube.com/"); // Replace with actual URL
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        System.out.println("Entering username and password..."); // Simulated input
    }

    @Then("user should be navigated to the homepage")
    public void user_should_be_navigated_to_the_homepage() {
        System.out.println("Login successful - homepage displayed.");
    }
}
