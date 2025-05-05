package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.RegisterAndLogin;
import utilities.ConfigReader;
import utilities.GWD;

public class UserLoginSteps {
    RegisterAndLogin ral = new RegisterAndLogin();

    @When("The user fills in the login information and clicks the login button")
    public void theUserFillsInTheLoginInformationAndClicksTheLoginButton() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ral.loginUsername));
        ral.mySendKeys(ral.loginUsername, ConfigReader.getProperty("username"));
        ral.mySendKeys(ral.loginPassword, ConfigReader.getProperty("password"));
        ral.myClick(ral.loginButton);
    }

    @Then("The user should be redirected to the account overview page")
    public void theUserShouldBeRedirectedToTheAccountOverviewPage() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ral.accountOverview));
        Assert.assertTrue(ral.accountOverview.isDisplayed());
    }
}