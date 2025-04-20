package fedex_totalsales.Tests;

import org.openqa.selenium.*;
import fedex_totalsales.Utilities.DriverSingleton;
import fedex_totalsales.Utilities.ReportSingleton;
import com.relevantcodes.extentreports.*;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class BaseTest {
    protected static WebDriver driver;
    protected static ExtentReports reports;
    protected ExtentTest test;

    @BeforeTest(alwaysRun = true)
    public void setupSuite() throws MalformedURLException {
        driver = DriverSingleton.getdriver();
        reports = ReportSingleton.getReport();
        if (reports == null) {
            throw new RuntimeException("Failed to initialize ExtentReports.");
        }
        driver.get("https://fedex-staging.totalsales.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (test != null) {
            test.log(LogStatus.INFO, "Test execution completed.");
            reports.endTest(test);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void teardownSuite() {
        System.out.println("teardownSuite() started.");
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while closing WebDriver: " + e.getMessage());
                if (test != null) {
                    test.log(LogStatus.ERROR, "Error while closing WebDriver: " + e.getMessage());
                }
            }
        }
        if (reports != null) {
            ReportSingleton.closeReport();
        }
    }


    protected void captureScreenshot(String screenshotName, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = "screenshots/";
            new File(screenshotDir).mkdirs();  // Ensure directory exists
            String filePath = screenshotDir + screenshotName + "_" + timestamp + ".png";
            FileUtils.copyFile(srcFile, new File(filePath));
            test.log(LogStatus.INFO, "Screenshot captured: " + screenshotName, test.addScreenCapture("../" + filePath));
        } catch (Exception e) {
            test.log(LogStatus.ERROR, "Error while capturing screenshot: " + e.getMessage());
        }
    }
}
