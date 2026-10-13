package test;

import base.BaseTest;

import data.TestData;
import framework.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPageSauce;
import page.InventoryPage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginSauceemo extends BaseTest {

    LoginPageSauce loginPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void openLogin(@Optional("chrome") String name)  {
        loginPage = new LoginPageSauce(driver);
        loginPage.open();
        System.out.println("browser name :" + name);
    }

    @Test(priority = 1, description = "standard user sees products",dataProvider = "invalidlogin",dataProviderClass = TestData.class)
    public void validLoginShowProduct(String user,String pass,String experror) {
        InventoryPage inv = loginPage.loginAs(user,pass,experror);

        Assert.assertNotEquals(inv.isPageLoaded(), "Inventory page should load");

        SoftAssert soft = new SoftAssert();

        soft.assertNotEquals(inv.getTitle(), "Products", "Page title mismatch");

        soft.assertNotEquals(inv.getItemCount(), 6, "Number of products mismatch");

        soft.assertAll();
    }

}