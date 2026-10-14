package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import framework.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentTestLisner implements ISuiteListener, ITestListener {

    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();
    private static ExtentReports extent;
    private static Path reportPath;

    public static ExtentTest getTest() {
        return TEST.get();
    }

    @Override
    public void onStart(ISuite suite) {
        String suiteName = (suite != null && suite.getName() != null) ? suite.getName() : "Suite";
        extent = createInstance(suiteName);

        if (extent == null) {
           return;
        }

        extent.setSystemInfo("Browser", valueOrConfig(getSuiteParameter(suite, "browser"), "browser"));
        extent.setSystemInfo("Environment", valueOrConfig(getSuiteParameter(suite, "env"), "env"));
        extent.setSystemInfo("Tester", getConfig("tester"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java", System.getProperty("java.version"));
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
           extent.flush();
        }

        if (reportPath != null) {
           System.out.println("Extent report : " + reportPath.toAbsolutePath());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        startEntry(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = entry(result);
        if (test != null) {
           test.pass("Passed");
        }
        TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = entry(result);
        if (test != null) {
           test.fail(result.getThrowable());
           attachScreenShot(test);
        }
        TEST.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = entry(result);
        if (test != null) {
           if (result.getThrowable() != null) {
               test.skip(result.getThrowable());
           } else {
               test.skip("Test skipped");
           }
        }
        TEST.remove();
    }

    private void startEntry(ITestResult result) {
        if (extent == null) {
           return;
        }

        ExtentTest test = extent.createTest(testName(result), result.getMethod().getDescription());
        if (test == null) {
           return;
        }

        String[] groups = result.getMethod().getGroups();
        if (groups != null && groups.length > 0) {
           test.assignCategory(groups);
        }

        String browser = getBrowserFromContext(result);
        if (browser != null) {
           test.assignDevice(browser);
        }

        TEST.set(test);
    }

    private ExtentTest entry(ITestResult result) {
        ExtentTest test = TEST.get();
        if (test == null) {
           startEntry(result);
           test = TEST.get();
        }
        return test;
    }

    private static String testName(ITestResult result) {
        String name = result.getMethod().getMethodName();
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
           StringBuilder sb = new StringBuilder(name);
           sb.append("(");
           for (int i = 0; i < parameters.length; i++) {
               sb.append(parameters[i]);
               if (i < parameters.length - 1) {
                   sb.append(", ");
               }
           }
           sb.append(")");
           name = sb.toString();
        }
        return name;
    }

    private static String valueOrConfig(String value, String configKey) {
        return (value != null && !value.isBlank()) ? value : getConfig(configKey);
    }

    private static String getConfig(String configKey) {
        String value = System.getProperty(configKey);
        return value != null ? value : "not-set";
    }

    private static String getSuiteParameter(ISuite suite, String key) {
        if (suite == null || suite.getXmlSuite() == null) {
           return null;
        }
        return suite.getXmlSuite().getParameter(key);
    }

    private static String getBrowserFromContext(ITestResult result) {
        if (result.getTestContext() == null || result.getTestContext().getCurrentXmlTest() == null) {
           return null;
        }
        return valueOrConfig(result.getTestContext().getCurrentXmlTest().getParameter("browser"), "browser");
    }

    private static ExtentReports createInstance(String suiteName) {
        ExtentReports report = new ExtentReports();
        String safeSuiteName = suiteName == null ? "suite" : suiteName.replaceAll("[^a-zA-Z0-9._-]", "_");

        try {
           Path reportDir = Path.of("reports");
           Files.createDirectories(reportDir);
           reportPath = reportDir.resolve("extent_" + safeSuiteName + "_" +
                   LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".html");
           report.attachReporter(new ExtentSparkReporter(reportPath.toFile()));
        } catch (IOException e) {
           reportPath = null;
        }

        return report;
    }


    private static void attachScreenShot(ExtentTest test) {
        if (test != null) {
            try {
                WebDriver driver = DriverManager.getDriver();

                if (driver != null) {
                    String base64Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                    test.fail("Screenshot",
                            com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
                } else {
                    test.info("Screenshot skipped: WebDriver instance was null.");
                }
            } catch (Exception e) {
                test.info("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }
}