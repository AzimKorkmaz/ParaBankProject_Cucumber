package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GWD;
import utilities.ReusableMethods;

public class RegisterAndLogin extends ReusableMethods {
    public Actions action;

    public RegisterAndLogin() {
        PageFactory.initElements(GWD.getDriver(), this);
        this.action = new Actions(GWD.getDriver());
    }

    @FindBy(linkText = "Register")
    public WebElement registerButton;

    @FindBy(id = "customer.firstName")
    public WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    public WebElement lastNameField;

    @FindBy(id = "customer.address.street")
    public WebElement addressField;

    @FindBy(id = "customer.address.city")
    public WebElement cityField;

    @FindBy(id = "customer.address.state")
    public WebElement stateField;

    @FindBy(id = "customer.address.zipCode")
    public WebElement zipCodeField;

    @FindBy(id = "customer.phoneNumber")
    public WebElement phoneNumberField;

    @FindBy(id = "customer.ssn")
    public WebElement ssnField;

    @FindBy(xpath = "(//input[@type='submit'])[2]")
    public WebElement registerSubmitButton;

    @FindBy(id = "customer.username")
    public WebElement registerUsernameField;

    @FindBy(id = "customer.password")
    public WebElement registerPasswordField;

    @FindBy(id = "repeatedPassword")
    public WebElement confirmPasswordField;

    @FindBy(id = "customer.username.errors")
    public WebElement usernameErrorMessage;

    @FindBy(css = "[id='rightPanel']>h1")
    public WebElement registerCheck;

    @FindBy(css = "[class='login']>input[name='username']")
    public WebElement loginUsername;

    @FindBy(css = "[class='login']>input[name='password']")
    public WebElement loginPassword;

    @FindBy(css = "[class='login']>input[type='submit']")
    public WebElement loginButton;

    @FindBy(css = "[id='leftPanel']>h2")
    public WebElement loginCheck;

    @FindBy(xpath = "//h1[text()='Error!']")
    public WebElement errorMessage;

    @FindBy(xpath = "//h1[contains(text(), 'Accounts Overview')]")
    public WebElement accountOverview;

    public boolean alreadyAdded() {
        try {
            return usernameErrorMessage.getText().contains("already");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}