package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

public class LoanPage extends ReusableMethods {
    public Actions action;

    public LoanPage() {
        PageFactory.initElements(GWD.getDriver(), this);
        this.action = new Actions(GWD.getDriver());
    }

    @FindBy(xpath = "//h1[contains(@class, 'title') and contains(text(), 'Apply for a Loan')]")
    public WebElement loanApplicationHeader;

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
}