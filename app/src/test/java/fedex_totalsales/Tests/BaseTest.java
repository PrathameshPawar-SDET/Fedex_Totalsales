package fedex_totalsales.Tests;
import org.openqa.selenium.WebDriver;
import fedex_totalsales.Utilities.DriverSingleton;
import fedex_totalsales.Utilities.ReportSingleton;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
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
    public void setupSuite() throws MalformedURLException, InterruptedException {
        driver = DriverSingleton.getdriver();
        reports = ReportSingleton.getReport();
        driver.get("https://fedex-staging.totalsales.com/");
        Thread.sleep(2000);
    }

//    @BeforeClass(alwaysRun = true)
//    public void setup() {
//
//    }

//    @AfterClass(alwaysRun = true)
//    public void teardown() {
//
//    }

    @AfterSuite(alwaysRun = true)
    public void teardownSuite() {
        captureScreenshot("EndOfTest");
        if (driver != null) {
            driver.quit();
        }
        if (reports != null) {
            reports.flush();
            reports.close();
        }
    }

    protected void captureScreenshot(String screenshotName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = "screenshots/" + screenshotName + "_" + timestamp + ".png";
            FileUtils.copyFile(srcFile, new File(filePath));
            test.log(LogStatus.INFO, "Screenshot captured: " + screenshotName,
                    test.addScreenCapture("../" + filePath));
        } catch (IOException e) {
            test.log(LogStatus.ERROR, "Error while capturing screenshot: " + e.getMessage());
        }
    }
}
