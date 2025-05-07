package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

public class DialogContent extends ReusableMethods {
    public Actions action;

    public DialogContent() {
        PageFactory.initElements(GWD.getDriver(), this);
        this.action = new Actions(GWD.getDriver());
    }

    ///     Bill Payment Service LOCATORS

    @FindBy(xpath = "//h1[text()='Bill Payment Service']")
    public WebElement billPayPage;

    @FindBy(name = "payee.name")
    public WebElement payeeNameField;

    @FindBy(name = "payee.address.street")
    public WebElement payeeAddressStreetField;

    @FindBy(name = "payee.address.city")
    public WebElement payeeAddressCityField;

    @FindBy(name = "payee.address.state")
    public WebElement payeeAddressStateField;

    @FindBy(name = "payee.address.zipCode")
    public WebElement payeeAddressZipCodeField;

    @FindBy(name = "payee.phoneNumber")
    public WebElement payeePhoneNumberField;

    @FindBy(name = "payee.accountNumber")
    public WebElement payeeAccountNumberField;

    @FindBy(name = "verifyAccount")
    public WebElement verifyAccountField;

    @FindBy(name = "amount")
    public WebElement amountField;

    @FindBy(name = "fromAccountId")
    public WebElement fromAccountIdField;

    @FindBy(xpath = "//input[@value='Send Payment']")
    public WebElement sendPaymentButton;

    @FindBy(id = "billpayResult")
    public WebElement billPayResultField;

    @FindBy(xpath = "//div[@id='billpayResult']/h1")
    public WebElement billPayResultFieldText;

    ///    Accounts Overview

    @FindBy(id = "showOverview")
    public WebElement showOverview;

    @FindBy(xpath = "(//table[@id='accountTable']//td)[2]")
    public WebElement currentBalance;

    @FindBy(xpath = "//table[@id='accountTable']//a")
    public WebElement account;

    ///     Account Details

    @FindBy(xpath = "//td[@id='balance']")
    public WebElement lastAvailable;

    @FindBy(id = "accountDetails")
    public WebElement accountDetailsText;

    ///     Account Activity

    @FindBy(xpath = "//a[contains(text(),'Bill Payment to')]")
    public WebElement billPaymentTransaction;

    @FindBy(xpath = "//h1[@class='title']")
    public WebElement transactionDetailsText;

    @FindBy(xpath = "//td[contains(text(),'Bill Payment to TEDAS')]")
    public WebElement tedasTransactionText;

    @FindBy(xpath = "//td[contains(text(),'Bill Payment to IGDAS')]")
    public WebElement igdasTransactionText;

    ///   Update contact Info
    @FindBy(xpath = "//td[contains(text(),'Bill Payment to ASAT')]")
    public WebElement asatTransactionText;
  
    @FindBy(xpath = "//input[@value='Update Profile']")
    public WebElement updateProfileButton;

    @FindBy(xpath = "//span[@id='lastName-error']")
    public WebElement requiredMessage;

    @FindBy(xpath = "//h1[@class='title' and text()='Profile Updated']")
    public WebElement profileUpdated;
}