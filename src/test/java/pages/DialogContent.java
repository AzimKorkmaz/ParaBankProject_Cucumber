package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

import java.util.List;

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
    public WebElement payeeAddressZipCodeieldField;

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
    public WebElement billpayResultField;

    @FindBy(xpath = "//div[@id='billpayResult']/h1")
    public WebElement billpayResultFieldText;

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
    public List<WebElement> transactionTable;


}