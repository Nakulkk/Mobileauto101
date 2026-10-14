package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.InventoryPage;
import page.LoginPageSauce;

public class LoginSauceemo extends BaseTest {

    private LoginPageSauce loginPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void openLogin(@Optional("chrome") String name) {
        loginPage = new LoginPageSauce(driver);
        loginPage.open();
        System.out.println("browser name :" + name);
    }

    @Test(priority = 1, description = "standard user sees products")
    public void validLoginShowProduct() {
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce", "");

        Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory page should load");
        Assert.assertEquals(inventoryPage.getTitle(), "Product", "The inventory page title should match");
    }
}

