package stepDefinitions;

import io.cucumber.java.en.*;
import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RegisterAndLogin;
import utilities.ConfigReader;
import utilities.GWD;

import java.util.Locale;

public class UserRegistrationSteps {
    RegisterAndLogin ral = new RegisterAndLogin();
    Faker faker = new Faker(new Locale("en-US"));

    @Given("The user navigates to the ParaBank website")
    public void theUserNavigatesToTheParaBankWebsite() {
        GWD.getDriver().get(ConfigReader.getProperty("homePageUrl"));
    }

    @When("The user clicks on the register button and then fills the information areas with the customer data and clicks on the submit button")
    public void theUserClicksOnTheRegisterButtonAndThenFillsTheInformationAreasWithTheCustomerDataAndClicksOnTheSubmitButton() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ral.registerButton));
        GWD.getWait().until(ExpectedConditions.elementToBeClickable(ral.registerButton));
        ral.myClick(ral.registerButton);
        do {
            GWD.getWait().until(ExpectedConditions.elementToBeClickable(ral.registerSubmitButton));
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
            GWD.getWait().until(ExpectedConditions.visibilityOf(ral.registerPasswordField));
            ral.mySendKeys(ral.registerPasswordField, ConfigReader.getProperty("password"));
            GWD.getWait().until(ExpectedConditions.visibilityOf(ral.confirmPasswordField));
            ral.mySendKeys(ral.confirmPasswordField, ConfigReader.getProperty("password"));

            GWD.getWait().until(ExpectedConditions.elementToBeClickable(ral.registerSubmitButton));
            ral.myClick(ral.registerSubmitButton);
        }
        while (ral.alreadyAdded());
    }

    @Then("Success message should be displayed")
    public void successMessageShouldBeDisplayed() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ral.registerCheck));
        ral.verifyContainsMessage(ral.registerCheck, ConfigReader.getProperty("username"));
    }
}