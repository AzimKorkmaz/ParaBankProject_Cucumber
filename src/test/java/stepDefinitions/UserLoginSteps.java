package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.LeftNav;
import pages.RegisterAndLogin;
import utilities.ConfigReader;
import utilities.GWD;

public class UserLoginSteps {
    RegisterAndLogin ral = new RegisterAndLogin();
    LeftNav ln = new LeftNav();

    @When("The user clicks on the logout button")
    public void theUserClicksOnTheLogoutButton() {
        ln.myClick(ln.logOutButton);
    }

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

    @When("The user attempts to log in with incorrect login credentials")
    public void theUserAttemptsToLogInWithIncorrectLoginCredentials() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ral.loginUsername));
        ral.mySendKeys(ral.loginUsername, ConfigReader.getProperty("wrongUsername"));
        ral.mySendKeys(ral.loginPassword, ConfigReader.getProperty("wrongPassword"));
        ral.myClick(ral.loginButton);
    }

    @Then("An error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ral.errorMessage));
        Assert.assertTrue(ral.errorMessage.isDisplayed());
    }
}
