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
/// Bill Payment Service LOCATORS
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

    @FindBy(name = "amount")
    public WebElement amountField;

    @FindBy(name = "fromAccountId")
    public WebElement fromAccountIdField;

    @FindBy(xpath = "//input[@value='Send Payment']")
    public WebElement SendPaymentButton;
}