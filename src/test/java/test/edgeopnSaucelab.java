package test;

import base.BaseTest;
import data.TestData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import page.InventoryPage;
import page.LoginPageSauce;


public class edgeopnSaucelab extends BaseTest {
    LoginPageSauce loginPage;
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void openLogin(@Optional("chrome") String name) {
        loginPage = new LoginPageSauce(driver);
        loginPage.open();
        System.out.println("browser name :" + name);
    }

    @Test(priority = 1, description = "standard user sees products", dataProvider = "invalidLoginsExcel", dataProviderClass = TestData.class)
    public void validLoginShowProduct(String name,String pss,String error) {
        InventoryPage inv = loginPage.loginAs(name,pss,error);

        loginPage.loginAs(name, pss, error);
        Assert.assertEquals(loginPage.url(), "https://www.saucedemo.com/", "Inventory page should load");
    }
}
