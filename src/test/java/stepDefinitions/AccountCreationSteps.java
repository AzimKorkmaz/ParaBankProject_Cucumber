package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.DialogContent;
import pages.LeftNav;
import utilities.ConfigReader;
import utilities.GWD;

public class AccountCreationSteps {
    LeftNav ln = new LeftNav();
    DialogContent dc = new DialogContent();

    @When("The user clicks on the Open new account area")
    public void theUserClicksOnTheOpenNewAccountArea() {
        ln.myClick(ln.openNewAccount);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.newAccountPageControl));
    }

    @Then("The user selects account type")
    public void theUserSelectsAccountType() {
        dc.selectByText(dc.selectMenu, ConfigReader.getProperty("accountType"));
    }

    @And("the user clicks the open new account button")
    public void theUserClicksTheOpenNewAccountButton() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.newAccount));
        GWD.getWait().until(ExpectedConditions.elementToBeClickable(dc.newAccount));
        dc.myClick(dc.newAccount);
    }

    @And("the user should see the message that the account was created and the account number on the screen")
    public void theUserShouldSeeTheMessageThatTheAccountWasCreatedAndTheAccountNumberOnTheScreen() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.accountOpenedText));
        Assert.assertTrue(dc.accountOpenedText.isDisplayed(), "message not displayed");
        Assert.assertTrue(dc.accountNumberClick.isDisplayed(), "account number not displayed");
    }
}