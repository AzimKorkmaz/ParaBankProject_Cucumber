package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.DialogContent;
import pages.LeftNav;
import pages.RegisterAndLogin;
import utilities.ConfigReader;
import utilities.GWD;

import java.util.Locale;

public class ContactInfoUpdateSteps {
    RegisterAndLogin ral = new RegisterAndLogin();
    LeftNav ln = new LeftNav();
    DialogContent dc=new DialogContent();
    Faker faker = new Faker(new Locale("en-US"));
    String surName= ConfigReader.getProperty("surName");
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
        Assert.assertTrue(dc.requiredMessage.isDisplayed(),"No visible message");
    }

    @Then("User will update the relevant field")
    public void userWillUpdateTheRelevantField() {
        ral.myClick(ral.lastNameField);
        ral.lastNameField.sendKeys(surName);
    }

    @And("User will see the update message")
    public void userWillSeeTheUpdateMessage() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.profileUpdated));
        Assert.assertTrue(dc.profileUpdated.isDisplayed(),"No visible message");
    }
}
