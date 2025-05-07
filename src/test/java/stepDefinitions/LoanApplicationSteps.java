package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.LeftNav;
import pages.LoanPage;
import utilities.ConfigReader;
import utilities.GWD;

import java.util.Locale;

public class LoanApplicationSteps {
    LeftNav ln = new LeftNav();
    LoanPage loanPage = new LoanPage();
    Faker faker = new Faker(new Locale("en-US"));
    
    @Given("User clicks on the Request Loan button")
    public void userClicksOnTheRequestLoanButton() {
        GWD.getWait().until(ExpectedConditions.elementToBeClickable(ln.requestLoanButton));
        ln.myClick(ln.requestLoanButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.loanApplicationHeader));
        Assert.assertTrue(loanPage.loanApplicationHeader.isDisplayed());
    }
    
    @When("User applies for a loan")
    public void userAppliesForALoan() {
        String randomLoanAmount = String.valueOf(faker.number().numberBetween(1000, 5000));
        String randomDownPayment = String.valueOf(faker.number().numberBetween(500, 1000));
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.loanAmountField));
        loanPage.mySendKeys(loanPage.loanAmountField, randomLoanAmount);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.downPaymentField));
        loanPage.mySendKeys(loanPage.downPaymentField, randomDownPayment);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.fromAccountSelect));
        Select select = new Select(loanPage.fromAccountSelect);
        if (select.getOptions().isEmpty()) {
            throw new RuntimeException("No accounts available for loan application");
        }
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.applyNowButton));
        loanPage.myClick(loanPage.applyNowButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.loanStatus));
    }
    
    @When("User applies for a loan with invalid values")
    public void userAppliesForALoanWithInvalidValues() {
        String invalidLoanAmount = String.valueOf(faker.number().numberBetween(10000, 50000));
        String invalidDownPayment = String.valueOf(faker.number().numberBetween(1, 10));
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.loanAmountField));
        loanPage.mySendKeys(loanPage.loanAmountField, invalidLoanAmount);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.downPaymentField));
        loanPage.mySendKeys(loanPage.downPaymentField, invalidDownPayment);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.fromAccountSelect));
        Select select = new Select(loanPage.fromAccountSelect);
        if (select.getOptions().isEmpty()) {
            throw new RuntimeException("No accounts available for loan application");
        }
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.applyNowButton));
        loanPage.myClick(loanPage.applyNowButton);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.loanStatus));
    }
    
    @Then("User should see the loan is approved")
    public void userShouldSeeTheLoanIsApproved() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.approvedMessage));
        Assert.assertTrue(loanPage.approvedMessage.isDisplayed());
    }
    
    @Then("User should see the loan is denied")
    public void userShouldSeeTheLoanIsDenied() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.deniedMessage));
        Assert.assertTrue(loanPage.deniedMessage.isDisplayed());
    }
    
    @And("User should see the denial reason")
    public void userShouldSeeTheDenialReason() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.denialReason));
        Assert.assertTrue(loanPage.denialReason.isDisplayed());
    }
    
    @And("User should see the new account number")
    public void userShouldSeeTheNewAccountNumber() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.newAccountNumber));
        String accountNumber = loanPage.newAccountNumber.getText();
        Assert.assertTrue(accountNumber.matches("\\d+"), "Account number should be numeric");
        ConfigReader.saveToConfig("newLoanAccountNumber", accountNumber);
    }
    
    @Then("User clicks on the new account number")
    public void userClicksOnTheNewAccountNumber() {
        GWD.getWait().until(ExpectedConditions.elementToBeClickable(loanPage.newAccountNumber));
        loanPage.myClick(loanPage.newAccountNumber);
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.noTransactionsMessage));
    }
    
    @And("User verifies there are no transactions in the new account")
    public void userVerifiesThereAreNoTransactionsInTheNewAccount() {
        GWD.getWait().until(ExpectedConditions.visibilityOf(loanPage.noTransactionsMessage));
        Assert.assertTrue(loanPage.noTransactionsMessage.isDisplayed(), 
            "No transactions message should be displayed");
    }
} 