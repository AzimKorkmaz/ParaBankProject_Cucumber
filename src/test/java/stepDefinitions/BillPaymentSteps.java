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
        dc.mySendKeys(dc.payeeAddressZipCodeieldField, faker.address().zipCode());
        dc.mySendKeys(dc.payeePhoneNumberField, faker.phoneNumber().phoneNumber());
        String acount = "654321";
        dc.mySendKeys(dc.payeeAccountNumberField, acount);
        dc.mySendKeys(dc.verifyAccountField, acount);
        String pay = String.valueOf(faker.number().numberBetween(20, 50));
        ConfigReader.saveToConfig("pay", pay);
        dc.mySendKeys(dc.amountField, pay);
    }

    @And("clicks the SendPayment button")
    public void clicksTheSendPaymentButton() {
        dc.myClick(dc.sendPaymentButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.billpayResultField));
        //Assert.assertTrue(dc.billPayPage.isDisplayed());
    }

    @Then("The user clicks on the Accounts Overview button")
    public void theUserClicksOnTheAccountsOverviewButton() {
        ln.myClick(ln.accountsOverviewButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.showOverview));
    }

    @And("clicks on your account to check the payment")
    public void clicksOnYourAccountToCheckThePayment() {
        dc.myClick(dc.account);
//        String lastBalanceStr=dc.lastAvailable.getText().trim().replaceAll("[^0-9.]", "");
//        System.out.println("lastBalanceStr = " + lastBalanceStr);
//        List<WebElement> transactionTableList = dc.transactionTable;
//        String deneme = transactionTableList.getLast().getText();

//        double lastBalance = Double.parseDouble(dc.lastAvailable.getText().trim().replaceAll("[^0-9.]", ""));
//        System.out.println("lastBalance = " + lastBalance);
//        double firstBalance = ConfigReader.getIntProperty("currentBalance");
//        int actualPayment = ConfigReader.getIntProperty("pay");
//        int expectedPayment = (int) (firstBalance - lastBalance);
//        Assert.assertEquals(actualPayment,expectedPayment,"Error in payment");
    }

    @And("save the current balance for checking")
    public void saveTheCurrentBalanceForChecking() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.currentBalance));
        String currentBalance = dc.currentBalance.getText().trim().replaceAll("[^0-9.]", "");
        ConfigReader.saveToConfig("currentBalance", currentBalance);
    }
}
