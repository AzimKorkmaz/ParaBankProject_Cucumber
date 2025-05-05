package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.LeftNav;
import pages.RegisterAndLogin;
import utilities.*;

import java.util.List;
import java.util.Locale;

public class UserLoginSteps {
    RegisterAndLogin ral = new RegisterAndLogin();
    LeftNav ln = new LeftNav();
    Faker faker = new Faker(new Locale("en-US"));


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

        List<WebElement> errorMessages = (ral.errorMessage);

        if (errorMessages.size() > 0) {
            do {
                GWD.getWait().until(ExpectedConditions.visibilityOf(ral.registerButton));
                GWD.getWait().until(ExpectedConditions.elementToBeClickable(ral.registerButton));
                ral.myClick(ral.registerButton);
                GWD.getWait().until(ExpectedConditions.visibilityOf(ral.firstNameField));
                ral.mySendKeys(ral.firstNameField, faker.name().firstName());
                ral.mySendKeys(ral.lastNameField, faker.name().lastName());
                ral.mySendKeys(ral.addressField, faker.address().fullAddress());
                ral.mySendKeys(ral.cityField, faker.address().city());
                ral.mySendKeys(ral.stateField, faker.address().state());
                ral.mySendKeys(ral.zipCodeField, faker.address().zipCode());
                ral.mySendKeys(ral.phoneNumberField, faker.phoneNumber().cellPhone());
                ral.mySendKeys(ral.ssnField, faker.idNumber().ssnValid());

                ConfigReader.updateProperty("username");
                ral.mySendKeys(ral.registerUsernameField, ConfigReader.getProperty("username"));

                ConfigReader.updateProperty("password");
                ral.mySendKeys(ral.registerPasswordField, ConfigReader.getProperty("password"));
                ral.mySendKeys(ral.confirmPasswordField, ConfigReader.getProperty("password"));
                GWD.getWait().until(ExpectedConditions.elementToBeClickable(ral.registerSubmitButton));
                ral.myClick(ral.registerSubmitButton);
            } while (ral.alreadyAdded());
        }
    }

    @Then("The user should be redirected to the account overview page")
    public void theUserShouldBeRedirectedToTheAccountOverviewPage() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ln.welcomeText));
        Assert.assertTrue(ln.welcomeText.isDisplayed());
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
        GWD.getWait().until(ExpectedConditions.visibilityOf(ral.errorMessageText));
        Assert.assertTrue(ral.errorMessageText.isDisplayed());
    }
}
