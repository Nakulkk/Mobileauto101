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
    @Parameters("browser1")
    public void openLogin(@Optional("chrome") String name) {
        loginPage = new LoginPageSauce(driver);
        loginPage.open();
        System.out.println("browser name :" + name);
    }

    @Test(priority = 1, description = "standard user sees products", dataProvider = "invalidLogin",dataProviderClass = TestData.class)
    public void validLoginShowProduct(String name,String pss,String error) {
        InventoryPage inv = loginPage.loginAs(name,pss,error);

        Assert.assertTrue(inv.isPageLoaded(), "Inventory page should load");

        SoftAssert soft = new SoftAssert();

        soft.assertEquals(inv.getTitle(), "Products", "Page title mismatch");

        soft.assertEquals(inv.getItemCount(), 6, "Number of products mismatch");

        soft.assertAll();
    }
}
