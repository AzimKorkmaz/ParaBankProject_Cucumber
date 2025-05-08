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
    public WebElement openNewAccount;

    @FindBy(linkText = "Accounts Overview")
    public WebElement accountsOverviewButton;

    @FindBy(linkText = "Bill Pay")
    public WebElement billPayButton;

    @FindBy(linkText = "Update Contact Info")
    public WebElement updateContactInfoButton;

    @FindBy(linkText = "Request Loan")
    public WebElement requestLoanButton;

    @FindBy(linkText = "Log Out")
    public WebElement logoutButton;

    @FindBy(xpath = "//b[text()='Welcome']")
    public WebElement welcomeText;

    @FindBy(xpath = "//p[contains(text(), 'Malik')]")
    public WebElement confirmationMessage;

    ///  loan application
    @FindBy(xpath = "//h1[contains(@class, 'title') and contains(text(), 'Apply for a Loan')]")
    public WebElement loanApplicationHeader;
}