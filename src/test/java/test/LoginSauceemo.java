package test;

import base.BasePage;
import base.BaseTest;
import com.sun.net.httpserver.Authenticator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.InventoryPage;
import java.time.Duration;
import page.LoginPage;
import page.InventoryPage;
public class LoginSauceemo extends LoginPage {

    LoginPage loginPage;

    public LoginSauceemo(WebDriver driver) {
        super(driver);
    }

    @BeforeMethod
    public void openlogin(){
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test(priority = 1, description = "standard user sees products")
    public void ValidLoginshowproduct(){

        InventoryPage inv = loginPage.loginas("standard_user", "secret_sauce");

        Assert.assertTrue(Authenticator.Retry.util(inv::isPageLoaded, Duration.ofSeconds(5)), "inventory page should load");

        SoftAssert soft = new SoftAssert();

        soft.assertEquals(inv.title(), "Products", "Page title mismatch");

        soft.assertEquals(inv.itemCount(), 6, "Number of products mismatch");

        soft.assertAll();
    }
}