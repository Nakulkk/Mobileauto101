package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void testGoogleTitle() {
        driver.get("https://www.google.com");
        String actualTitle = driver.getTitle();
        System.out.println("Page Title is: " + actualTitle);

        Assert.assertTrue(actualTitle.contains("Google"), "Title match nahi hua!");
    }
}