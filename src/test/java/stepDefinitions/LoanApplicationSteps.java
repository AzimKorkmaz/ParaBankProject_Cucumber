package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.DialogContent;
import pages.LeftNav;
import utilities.ConfigReader;
import utilities.GWD;

import java.util.Locale;

public class LoanApplicationSteps {
    LeftNav ln = new LeftNav();
    DialogContent dc = new DialogContent();
    Faker faker = new Faker(new Locale("en-US"));
    
    @Given("User clicks on the Request Loan button")
    public void userClicksOnTheRequestLoanButton() {
        GWD.getWait().until(ExpectedConditions.elementToBeClickable(ln.requestLoanButton));
        ln.myClick(ln.requestLoanButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(ln.loanApplicationHeader));
        Assert.assertTrue(ln.loanApplicationHeader.isDisplayed());
    }
    
    @When("User applies for a loan")
    public void userAppliesForALoan() {
        String randomLoanAmount = String.valueOf(faker.number().numberBetween(1000, 2000));
        String randomDownPayment = String.valueOf(faker.number().numberBetween(200, 500));
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.loanAmountField));
        dc.mySendKeys(dc.loanAmountField, randomLoanAmount);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.downPaymentField));
        dc.mySendKeys(dc.downPaymentField, randomDownPayment);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.fromAccountSelect));
        Select select = new Select(dc.fromAccountSelect);
        if (select.getOptions().isEmpty()) {
            throw new RuntimeException("No accounts available for loan application");
        }
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.applyNowButton));
        dc.myClick(dc.applyNowButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.loanStatus));
    }
    
    @When("User applies for a loan with invalid values")
    public void userAppliesForALoanWithInvalidValues() {
        String invalidLoanAmount = String.valueOf(faker.number().numberBetween(10000, 50000));
        String invalidDownPayment = String.valueOf(faker.number().numberBetween(1, 10));
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.loanAmountField));
        dc.mySendKeys(dc.loanAmountField, invalidLoanAmount);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.downPaymentField));
        dc.mySendKeys(dc.downPaymentField, invalidDownPayment);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.fromAccountSelect));
        Select select = new Select(dc.fromAccountSelect);
        if (select.getOptions().isEmpty()) {
            throw new RuntimeException("No accounts available for loan application");
        }
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.applyNowButton));
        dc.myClick(dc.applyNowButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.loanStatus));
    }
    
    @Then("User should see the loan is approved")
    public void userShouldSeeTheLoanIsApproved() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.approvedMessage));
        Assert.assertTrue(dc.approvedMessage.isDisplayed());
    }
    
    @Then("User should see the loan is denied")
    public void userShouldSeeTheLoanIsDenied() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.deniedMessage));
        Assert.assertTrue(dc.deniedMessage.isDisplayed());
    }
    
    @And("User should see the denial reason")
    public void userShouldSeeTheDenialReason() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.denialReason));
        Assert.assertTrue(dc.denialReason.isDisplayed());
    }
    
    @And("User should see the new account number")
    public void userShouldSeeTheNewAccountNumber() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.newAccountNumber));
        String accountNumber = dc.newAccountNumber.getText();
        Assert.assertTrue(accountNumber.matches("\\d+"), "Account number should be numeric");
        ConfigReader.saveToConfig("newLoanAccountNumber", accountNumber);
    }
    
    @Then("User clicks on the new account number")
    public void userClicksOnTheNewAccountNumber() {
        GWD.getWait().until(ExpectedConditions.elementToBeClickable(dc.newAccountNumber));
        dc.myClick(dc.newAccountNumber);
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.noTransactionsMessage));
    }
    
    @And("User verifies there are no transactions in the new account")
    public void userVerifiesThereAreNoTransactionsInTheNewAccount() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(dc.noTransactionsMessage));
        Assert.assertTrue(dc.noTransactionsMessage.isDisplayed(),
            "No transactions message should be displayed");
    }
} 