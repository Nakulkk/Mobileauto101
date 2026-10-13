package page;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By titleHeading = By.xpath("//span[@data-test='title']");
    private final By inventoryItems = By.className("inventory_item");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return waitForVisibility(titleHeading).isDisplayed();
    }

    public String getTitle() {
        return waitForVisibility(titleHeading).getText();
    }

    public int getItemCount() {
        return driver.findElements(inventoryItems).size();
    }
}