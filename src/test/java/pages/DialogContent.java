package pages;

import org.eclipse.aether.spi.connector.Transfer;
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

    @FindBy(xpath = "//input[@value='Send Payment']")
    public WebElement sendPaymentButton;

    @FindBy(id = "billpayResult")
    public WebElement billPayResultField;

    @FindBy(id = "showOverview")
    public WebElement showOverview;

    @FindBy(xpath = "(//table[@id='accountTable']//td)[2]")
    public WebElement currentBalance;

    @FindBy(xpath = "//table[@id='accountTable']//a")
    public WebElement account;

    @FindBy(id = "accountDetails")
    public WebElement accountDetailsText;

    @FindBy(xpath = "//a[contains(text(),'Bill Payment to')]")
    public WebElement billPaymentTransaction;

    @FindBy(xpath = "//h1[@class='title']")
    public WebElement transactionDetailsText;

    @FindBy(xpath = "//td[contains(text(),'Bill Payment to TEDAS')]")
    public WebElement tedasTransactionText;

    @FindBy(xpath = "//td[contains(text(),'Bill Payment to IGDAS')]")
    public WebElement igdasTransactionText;

    @FindBy(xpath = "//td[contains(text(),'Bill Payment to ASAT')]")
    public WebElement asatTransactionText;

    @FindBy(xpath = "//input[@value='Update Profile']")
    public WebElement updateProfileButton;

    @FindBy(xpath = "//span[@id='lastName-error']")
    public WebElement requiredMessage;

    @FindBy(xpath = "//h1[@class='title' and text()='Profile Updated']")
    public WebElement profileUpdated;

    @FindBy(id = "fromAccountId")
    public WebElement fromAccountDropDown;

    @FindBy(id = "toAccountId")
    public WebElement toAccountDropDown;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement transferButton;

    @FindBy(xpath = "//h1[text()='Transfer Complete!']")
    public WebElement transferMessage;

    @FindBy(css = "div[id='showError'] h1[class='title']")
    public WebElement error;

    @FindBy(id = "accountId")
    public WebElement selectAccountDropDown;

    @FindBy(xpath = "(//a[contains(@href, 'activity.htm?id=')])[1]") //(//a[contains(@href, 'activity.htm?id=')])[2]
    public WebElement accounts;

    @FindBy(xpath = "//a[text()='Funds Transfer Received']")
    public WebElement fundsTransferReceived;

    @FindBy(xpath = "//td[@align='right']/following-sibling::td")
    public WebElement transactionId;

    @FindBy(id = "findById")
    public WebElement findButton;

    @FindBy(xpath = "(//td[@align='right']//following-sibling::td)[2]")
    public WebElement transferDate;

    @FindBy(id = "amount")
    public WebElement transferAmount;

    @FindBy(xpath = "(//h1[@class='title'])[1]")
    public WebElement accountsOverviewTitle;

    @FindBy(xpath = "(//td[@align='right']//following-sibling::td)[5]")
    public WebElement transactionAmount;

    @FindBy(xpath = "(//*[text()='Open New Account'])[2]")
    public WebElement newAccountPageControl;

    @FindBy(xpath = "//select[@id='type']")
    public WebElement selectMenu;

    @FindBy(xpath = "//div[@id='openAccountResult']//p")
    public WebElement accountOpenedText;

    @FindBy(xpath = "//a[@id='newAccountId']")
    public WebElement accountNumberClick;

    @FindBy(xpath = "//input[@value='Open New Account']")
    public WebElement newAccount;

    @FindBy(id = "amount")
    public WebElement loanAmountField;

    @FindBy(id = "downPayment")
    public WebElement downPaymentField;

    @FindBy(id = "fromAccountId")
    public WebElement fromAccountSelect;

    @FindBy(xpath = "//input[@type='button' and @class='button' and @value='Apply Now']")
    public WebElement applyNowButton;

    @FindBy(id = "loanStatus")
    public WebElement loanStatus;

    @FindBy(xpath = "//div[@id='loanRequestApproved']/p[contains(text(), 'Congratulations')]")
    public WebElement approvedMessage;

    @FindBy(xpath = "//div[@id='loanRequestDenied']/p[contains(@class, 'error')]")
    public WebElement deniedMessage;

    @FindBy(xpath = "//div[@id='loanRequestDenied']/p[contains(@class, 'error')]")
    public WebElement denialReason;

    @FindBy(id = "newAccountId")
    public WebElement newAccountNumber;

    @FindBy(id = "noTransactions")
    public WebElement noTransactionsMessage;

    @FindBy(css = "div[id='resultContainer'] h1[class='title']")
    public WebElement transactionResultsTitle;

    @FindBy(xpath = "//input[@id='transactionId']")
    public WebElement transactionIdField;
}