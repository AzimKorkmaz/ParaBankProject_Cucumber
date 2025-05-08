package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.DialogContent;
import pages.LeftNav;
import utilities.ConfigReader;
import utilities.GWD;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MoneyTransferSteps {
    LeftNav ln = new LeftNav();
    DialogContent dc = new DialogContent();
    Faker faker = new Faker();
    String expectedAmount = String.valueOf(faker.number().numberBetween(200, 300));

    @When("The user clicks on Transfer Funds button from home page")
    public void theUserClicksOnTransferFundsButtonFromHomePage() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(ln.transferFundsButton));
        ln.myClick(ln.transferFundsButton);
    }

    @And("The user fills all fields")
    public void theUserFillsAllFields() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.fromAccountDropDown));
        Assert.assertTrue(dc.fromAccountDropDown.isEnabled());
        Assert.assertTrue(dc.toAccountDropDown.isEnabled());

        GWD.getWait().until(ExpectedConditions.elementToBeClickable(dc.transferAmount));
        dc.myClick(dc.transferAmount);

        dc.mySendKeys(dc.transferAmount, expectedAmount);
    }

    @And("The user clicks Transfer button")
    public void theUserClicksTransferButton() {
        GWD.getWait().until(ExpectedConditions.elementToBeClickable(dc.transferButton));
        dc.myClick(dc.transferButton);
    }

    @Then("The user should see a transfer message and confirm successful transfer completion")
    public void theUserShouldSeeATransferMessageAndConfirmSuccessfulTransferCompletion() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.transferMessage));
        Assert.assertTrue("Transfer successful message not displayed", dc.transferMessage.isDisplayed());

        ln.myClick(ln.accountsOverviewButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.accountsOverviewTitle));
        dc.action.pause(Duration.ofSeconds(3));

        dc.myClick(dc.accounts);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.fundsTransferReceived));
        dc.myClick(dc.fundsTransferReceived);
        ConfigReader.saveToConfig("transactionId", dc.transactionId.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        // ZoneId zoneId = ZoneId.of("America/New_York");
        String today = LocalDate.now().format(formatter);

        System.out.println("Tarih  " + dc.transferDate.getText());
        Assert.assertEquals(dc.transferDate.getText(), today);
        String actualAmount = dc.transactionAmount.getText().replaceAll("[$,.]", "").substring(0, 3);
        System.out.println("Expected Amount: " + expectedAmount);
        System.out.println("Actual Amount: " + actualAmount);
        Assert.assertEquals(expectedAmount, actualAmount);
    }

    @When("The user does not enter anything to amount field")
    public void theUserDoesNotEnterAnythingToAmountField() {
        //dc.mySendKeys(dc.amountField, " ");
        dc.myClick(dc.transferButton);
    }

    @Then("The user should see error message on transfer funds page")
    public void theUserShouldSeeErrorMessageOnTransferFundsPage() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.error));
        Assert.assertTrue(dc.error.isDisplayed());
    }

    @When("The user clicks on Find Transactions button from homepage")
    public void theUserClicksOnFindTransactionsButtonFromHomepage() {
        ln.myClick(ln.accountsOverviewButton);
        dc.myClick(dc.accounts);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.fundsTransferReceived));
        dc.myClick(dc.fundsTransferReceived);
    }

    @And("The user selects an account and enters the Transaction ID")
    public void theUserSelectsAnAccountAndEntersTheTransactionID() {
        ln.myClick(ln.findTransactionsButton);

        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.transactionIdField));
        dc.myClick(dc.transactionIdField);
        dc.mySendKeys(dc.transactionIdField, ConfigReader.getProperty("transactionId"));

        Select selectAnAccount = new Select(dc.selectAccountDropDown);
        selectAnAccount.selectByIndex(1);
    }

    @And("The user click Find Transactions button")
    public void theUserClickFindTransactionsButton() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.findButton));
        ln.myClick(dc.findButton);
    }

    @Then("The user should be redirected Account Details page")
    public void theUserShouldBeRedirectedAccountDetailsPage() {
        Assert.assertTrue(dc.transactionResultsTitle.isDisplayed());
    }
}