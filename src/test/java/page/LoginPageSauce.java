package page;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void enterUsername(String username) {
        waitForVisibility(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForVisibility(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        waitForVisibility(loginButton).click();
    }

    public InventoryPage loginas(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new InventoryPage(driver);
    }
}

//git init
//git add README.md
//git commit -m "first commit"
//git branch -M main
//git remote add origin https://github.com/PrajaktKharat/mobileauto101.git
//git push -u origin main