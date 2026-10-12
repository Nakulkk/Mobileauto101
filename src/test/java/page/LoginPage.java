package page;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {


    private final By emailField = By.xpath("//input[@data-qa='login-email']");
    private final By passwordField = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String url() {
        return "https://www.automationexercise.com/login";
    }


    public void enterEmail(String email) {
        waitForVisibility(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        waitForVisibility(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        waitForVisibility(loginButton).click();
    }

    public void performLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

}