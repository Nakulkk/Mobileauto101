package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BasePage implements PageInterface {
    protected WebDriver driver;

    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected static final Logger log = LogManager.getLogger(BasePage.class);


    @Override
    public boolean isPageLoaded() {
        return driver != null && !driver.getCurrentUrl().isBlank();
    }

    public WebElement waitForVisibility (By locator) {
        return driver.findElement(locator);
    }
}

