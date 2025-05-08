package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.*;
import utilities.*;

import java.util.*;

public class BillPaymentSteps {
    LeftNav ln = new LeftNav();
    DialogContent dc = new DialogContent();
    Faker faker = new Faker(new Locale("en-US"));

    @And("save the current balance for checking")
    public void saveTheCurrentBalanceForChecking() {
        ln.myClick(ln.accountsOverviewButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.currentBalance));
        String currentBalance = dc.currentBalance.getText().trim().replaceAll("[^0-9.]", "");
        ConfigReader.saveToConfig("currentBalance", currentBalance);
    }

    @Given("User clicks on the BillPay button")
    public void userClicksOnTheBillPayButton() {
        ln.myClick(ln.billPayButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.billPayPage));
        dc.verifyContainsMessage(dc.billPayPage, "Bill Payment Service");
    }

    @Then("The user enters the information of the {string} invoice to be paid")
    public void theUserEntersTheInformationOfTheInvoiceToBePaid(String institution) {
        dc.mySendKeys(dc.payeeNameField, institution);
        dc.mySendKeys(dc.payeeAddressStreetField, faker.address().streetAddress());
        dc.mySendKeys(dc.payeeAddressCityField, faker.address().city());
        dc.mySendKeys(dc.payeeAddressStateField, faker.address().state());
        dc.mySendKeys(dc.payeeAddressZipCodeField, faker.address().zipCode());
        dc.mySendKeys(dc.payeePhoneNumberField, faker.phoneNumber().phoneNumber());
        String account = "654321";
        dc.mySendKeys(dc.payeeAccountNumberField, account);
        dc.mySendKeys(dc.verifyAccountField, account);
        String pay = String.valueOf(faker.number().numberBetween(20, 50));
        ConfigReader.saveToConfig("pay", pay);
        dc.mySendKeys(dc.amountField, pay);
    }

    @And("clicks the SendPayment button")
    public void clicksTheSendPaymentButton() {
        dc.myClick(dc.sendPaymentButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.billPayResultField));
    }

    @Then("The user clicks on the Accounts Overview button")
    public void theUserClicksOnTheAccountsOverviewButton() {
        ln.myClick(ln.accountsOverviewButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.showOverview));
    }

    @And("checks the payment")
    public void checksThePayment() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.currentBalance));
        double lastBalance = Double.parseDouble(dc.currentBalance.getText().trim().replaceAll("[^0-9.]", ""));
        String  firstBalanceStr = ConfigReader.getProperty("currentBalance");
        double firstBalance = Double.parseDouble(firstBalanceStr);
        int actualPayment = ConfigReader.getIntProperty("pay");
        int expectedPayment = (int) (firstBalance - lastBalance);
        Assert.assertEquals(actualPayment,expectedPayment,"Error in payment");
    }

    @And("clicks on his account number to check {string}")
    public void clicksOnHisAccountNumberToCheck(String institution) {
        dc.myClick(dc.account);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.accountDetailsText));
        dc.myClick(dc.billPaymentTransaction);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.transactionDetailsText));
        boolean found = false;
        try {
            if (dc.tedasTransactionText.getText().contains("TEDAS")) found = true;
            System.out.println("Tedas: " + dc.tedasTransactionText.getText());
        } catch (Exception ignored) {}

        try {
            if (dc.igdasTransactionText.getText().contains("IGDAS")) found = true;
            System.out.println("Igdass: " + dc.igdasTransactionText.getText());
        } catch (Exception ignored) {}

        try {
            if (dc.asatTransactionText.getText().contains("ASAT")) found = true;
            System.out.println("Asat: " + dc.asatTransactionText.getText());
        } catch (Exception ignored) {}

        Assert.assertTrue(found, "No matching institution (TEDAS, IGDAS, ASAT) found in transaction text!");
    }
}