package fedex_totalsales.Tests;

import com.relevantcodes.extentreports.LogStatus;
import fedex_totalsales.Pages.HomePage;
import fedex_totalsales.Pages.superAdminLogin;
import fedex_totalsales.Utilities.DP;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class loginWithCredentials extends BaseTest {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    @Test(dataProvider = "excelReading", dataProviderClass = DP.class, description = "Verify login functionality", groups = "sanity", priority = 1)
    public void testcase01(String username, String password) {
        test = reports.startTest("testcase01 - Verify login for user: " + username);
        try {
            HomePage home = new HomePage(driver);
            home.navigationToHome();
            test.log(LogStatus.INFO, "Navigated to Homepage");

            home.navigatetoSALogin();
            test.log(LogStatus.INFO, "Navigated to Super Admin Login page");

            superAdminLogin login = new superAdminLogin(driver);
            if (login.isheaderdisplayed()) {
                test.log(LogStatus.INFO, "Login header visible, entering credentials");
                login.enterCredentials(username, password);
                login.Login();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

                try {
                    if (home.verifylogin()) {
                        test.log(LogStatus.PASS, "Login successful for user: " + username);
                        captureScreenshot("SuccessfulLogin", home.helpMe);
                        home.logout();
                        test.log(LogStatus.INFO, "Logged out successfully");
                    } else {
                        String errorMsg = login.geterror();
                        if (errorMsg != null && !errorMsg.isEmpty()) {
                            test.log(LogStatus.FAIL, "Login failed with error: " + errorMsg);
                            captureScreenshot("LoginFailed", login.SubmitButton);
                        } else {
                            test.log(LogStatus.FAIL, "Login failed with no visible error message.");
                        }
                    }
                } catch (Exception e) {
                    test.log(LogStatus.ERROR, "Error verifying login for user: " + username + " - " + e.getMessage());
                }
            } else {
                test.log(LogStatus.FAIL, "Login header not displayed for user: " + username);
            }
        } catch (Exception e) {
            test.log(LogStatus.ERROR, "Unexpected exception for user: " + username + " - " + e.getMessage());
        } finally {
            test.log(LogStatus.INFO, "Finished execution for user: " + username);
        }
    }

}
