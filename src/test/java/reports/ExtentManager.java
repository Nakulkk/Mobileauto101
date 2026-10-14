package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {

    private static ExtentReports extent;
    private  static Path reportPath;

    public static synchronized ExtentReports createInstance(String suiteNmae) {
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String safeName = suiteNmae.replaceAll("[^a-zA-Z0-9]", "_");
        String reportFileName = safeName + "_" + stamp + ".html";
        reportPath = Path.of("test-output", "ExtentReports", reportFileName);

        try {
            Files.createDirectories(reportPath.getParent());
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create report directory: " + reportPath.getParent(), e);

      }

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath.toString());
        spark.config().getDocumentTitle();
        spark.config().setReportName(suiteNmae);
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;

    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports instance has not been created. Call createInstance() first.");

        }
        return extent;
    }

    public static synchronized Path getReportPath(){
        return reportPath;
    }

    public  static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

}
