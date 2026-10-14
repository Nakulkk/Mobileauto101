package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private DriverFactory(){

    }

    public static WebDriver create(String browser){
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        switch (browser.trim().toLowerCase()){
            case "chrome":{
                ChromeOptions options = new ChromeOptions();
                if (headless){
                    options.addArguments("--headless=new");
                }
                return new ChromeDriver(options);
            }

            case "edge":{
                EdgeOptions options = new EdgeOptions();
                if (headless){
                    options.addArguments("--headless=new");
                }
                return new EdgeDriver(options);
            }
            case "firefox":{
                FirefoxOptions options = new FirefoxOptions();
                if (headless){
                    options.addArguments("--headless=new");
                }
                return new FirefoxDriver(options);
            }
            default:
                throw new IllegalArgumentException("unsupport browser:" + browser+"use firefox ,chrome,edge");
        }

    }
}
