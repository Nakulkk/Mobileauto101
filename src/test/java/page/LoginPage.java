package page;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public LoginPage open() {
        driver.get(url());
        return this;
    }

    public LoginPage enterEmail(String email) {
        WebElement element = waitForVisibility(emailField);
        element.clear();
        element.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        WebElement element = waitForVisibility(passwordField);
        element.clear();
        element.sendKeys(password);
        return this;
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