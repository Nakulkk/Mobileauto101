package test;

import base.BaseTest;
import data.TestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.InventoryPage;
import page.LoginPageSauce;

public class FilefoxsauceDemo extends BaseTest {
    LoginPageSauce loginPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void openLogin(@Optional("chrome") String name) {
        loginPage = new LoginPageSauce(driver);
        loginPage.open();
        System.out.println("browser name :" + name);
    }

    @Test(priority = 1, description = "standard user sees products",dataProvider = "invalidLoginsCsv",dataProviderClass = TestData.class)
    public void validLoginShowProduct(String name,String pss,String error) {
        loginPage.loginAs(name, pss, error);
        Assert.assertEquals(loginPage.url(), "https://www.saucedemo.com/", "Inventory page should load");
    }
}