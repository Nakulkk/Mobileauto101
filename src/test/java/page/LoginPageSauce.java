package page;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageSauce extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPageSauce(WebDriver driver) {
        super(driver);
    }

    public String url() {
        return "https://www.saucedemo.com/";
    }

    public LoginPageSauce open() {
        driver.get(url());
        return this;
    }

    public LoginPageSauce enterUsername(String username) {
        WebElement element = waitForVisibility(usernameField);
        element.clear();
        element.sendKeys(username);
        return this;
    }

    public LoginPageSauce enterPassword(String password) {
        WebElement element = waitForVisibility(passwordField);
        element.clear();
        element.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        waitForVisibility(loginButton).click();
    }

    public InventoryPage loginAs(String username, String password, String experror) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new InventoryPage(driver);
    }


}