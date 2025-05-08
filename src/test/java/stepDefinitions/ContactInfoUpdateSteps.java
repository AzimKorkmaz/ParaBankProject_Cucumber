package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.DialogContent;
import pages.LeftNav;
import pages.RegisterAndLogin;
import utilities.ConfigReader;
import utilities.GWD;

public class ContactInfoUpdateSteps {
    RegisterAndLogin ral = new RegisterAndLogin();
    LeftNav ln = new LeftNav();
    DialogContent dc = new DialogContent();

    @Given("User click on the Update Contact Info button")
    public void userClickOnTheUpdateContactInfoButton() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ln.updateContactInfoButton));
        ln.myClick(ln.updateContactInfoButton);
    }

    @When("User clear the last name")
    public void userClearTheLastName() {
        ral.myClick(ral.lastNameField);
        ral.lastNameField.clear();
    }

    @Then("User click on the update profile button")
    public void userClickOnTheUpdateProfileButton() {
        dc.myClick(dc.updateProfileButton);
    }

    @And("User will visible an error message")
    public void userWillVisibleAnErrorMessage() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.requiredMessage));
        Assert.assertTrue(dc.requiredMessage.isDisplayed(), "No visible message");
    }

    @Then("User will update the relevant field")
    public void userWillUpdateTheRelevantField() {
        ral.myClick(ral.lastNameField);
        ral.mySendKeys(ral.lastNameField,ConfigReader.getProperty("surname"));
    }

    @And("User will see the update message")
    public void userWillSeeTheUpdateMessage() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.profileUpdated));
        Assert.assertTrue(dc.profileUpdated.isDisplayed(), "No visible message");
    }

    @And("User sees modified last name")
    public void userSeesModifiedLastName() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ln.confirmationMessage));
        Assert.assertTrue(ln.confirmationMessage.isDisplayed(), "No visible message");
    }
}