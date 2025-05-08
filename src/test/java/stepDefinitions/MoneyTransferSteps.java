package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.DialogContent;
import pages.LeftNav;
import utilities.ConfigReader;
import utilities.GWD;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MoneyTransferSteps {
    LeftNav ln = new LeftNav();
    DialogContent dc = new DialogContent();

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

        dc.mySendKeys(dc.transferAmount, ConfigReader.getProperty("transferAmount"));
        Select selectFromAccount = new Select(dc.fromAccountDropDown);
        selectFromAccount.selectByIndex(0);

        Select selectToAccount = new Select(dc.toAccountDropDown);
        selectToAccount.selectByIndex(0);
    }

    @And("The user clicks Transfer button")
    public void theUserClicksTransferButton() {
        dc.myClick(dc.transferButton);
    }

    @Then("The user should see a transfer message and confirm successful transfer completion")
    public void theUserShouldSeeATransferMessageAndConfirmSuccessfulTransferCompletion() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.transferMessage));
        Assert.assertTrue("Transfer successful message not displayed", dc.transferMessage.isDisplayed());

        ln.myClick(ln.accountsOverviewButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.accountsOverviewTitle));
        dc.action.pause(Duration.ofSeconds(3));
        List<WebElement> accountSize = dc.accounts;

        System.out.println("Size=  " + accountSize.size());
        dc.myClick(accountSize.getLast());

        dc.myClick(dc.fundsTransferReceived);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        // ZoneId zoneId = ZoneId.of("America/New_York");
        String today = LocalDate.now().format(formatter);

        System.out.println("Tarih  " + dc.transferDate.getText());
        Assert.assertEquals(dc.transferDate.getText(), today);

        Assert.assertEquals(dc.transactionAmount.getText().replaceAll("[$]", ""), ConfigReader.getProperty("transferAmount"));
    }

    @When("The user does not enter anything to amount field")
    public void theUserDoesNotEnterAnythingToAmountField() {
        dc.mySendKeys(dc.amountField, "");
    }

    @Then("The user should see {string} error message on transfer funds page")
    public void theUserShouldSeeErrorMessageOnTransferFundsPage(String message) {
        String actualMessage = dc.error.getText();
        Assert.assertEquals(message, actualMessage);
    }

    @When("The user clicks on Find Transactions button from homepage")
    public void theUserClicksOnFindTransactionsButtonFromHomepage() {
        ln.myClick(ln.findTransactionsButton);
    }

    @And("The user selects an account and enters the Transaction ID")
    public void theUserSelectsAnAccountAndEntersTheTransactionID() {
        ln.myClick(ln.accountsOverviewButton);
        dc.myClick(dc.accounts.get(1));

        dc.myClick(dc.fundsTransferReceived);

        String transactionId = dc.transactionId.getText();

        ln.myClick(ln.findTransactionsButton);

        Select selectAnAccount = new Select(dc.selectAccountDropDown);
        selectAnAccount.selectByIndex(0);

        dc.mySendKeys(dc.transactionId, transactionId);
    }

    @And("The user click Find Transactions button")
    public void theUserClickFindTransactionsButton() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.findButton));
        ln.myClick(dc.findButton);
    }

    @Then("The user should be redirected Account Details page")
    public void theUserShouldBeRedirectedAccountDetailsPage() {

    }
}