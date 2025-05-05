package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

public class LeftNav extends ReusableMethods {
    public Actions action;

    public LeftNav() {
        PageFactory.initElements(GWD.getDriver(), this);
        this.action = new Actions(GWD.getDriver());
    }

    @FindBy(linkText = "Open New Account")
    public WebElement openNewAccountBotton;

    @FindBy(linkText = "Accounts Overview")
    public WebElement accountsOverviewBotton;

    @FindBy(linkText = "Transfer Funds")
    public WebElement transferFundsBotton;

    @FindBy(linkText = "Bill Pay")
    public WebElement billPayBotton;

    @FindBy(linkText = "Find Transactions")
    public WebElement findTransactionsBotton;

    @FindBy(linkText = "Update Contact Info")
    public WebElement updateContactInfoBotton;

    @FindBy(linkText = "Request Loan")
    public WebElement requestLoanBotton;

    @FindBy(linkText = "Log Out")
    public WebElement logOutBotton;
}