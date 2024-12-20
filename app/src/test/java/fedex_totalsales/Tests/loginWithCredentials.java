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
        test = reports.startTest("testcase01 - Verify login flow");
        try {
            HomePage home = new HomePage(driver);
            test.log(LogStatus.INFO, "Navigating to Homepage");
            home.navigationToHome();
            test.log(LogStatus.INFO, "Navigating to Login page");
            home.navigatetoSALogin();

            superAdminLogin login = new superAdminLogin(driver);
            login.isheaderdisplayed();

            if (login.isheaderdisplayed()) {
                test.log(LogStatus.INFO, "Login Header is displayed, proceeding with login");
                login.enterCredentials(username, password);
                login.Login();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                // Adding a log here to check if verifylogin is being called
                test.log(LogStatus.INFO, "Calling verifylogin method for user: " + username);

                try {
                    if (home.verifylogin()) {
                        test.log(LogStatus.PASS, "Login successful for user: " + username);
//                        wait.until(ExpectedConditions.visibilityOf(home.Employee_text));
                        captureScreenshot("Successfulllogin", home.helpMe);
//                        Thread.sleep(2000);
                        home.logout();
                        test.log(LogStatus.INFO, "Logged out successfully");
                    } else {
                        test.log(LogStatus.INFO, "verifylogin returned false for user: " + username);

                        String errorMsg = login.geterror();
                        if (errorMsg != null && !errorMsg.isEmpty()) {
                            test.log(LogStatus.FAIL, "Login failed for user: " + username + " with error: " + errorMsg);
                            captureScreenshot("LoginFailedError", login.SubmitButton);
                        } else {
                            test.log(LogStatus.FAIL, "Login failed for user: " + username + " with no error message.");

                        }
                    }
                } catch (Exception e) {
                    test.log(LogStatus.ERROR, "Exception occurred while verifying login for user: " + username + " - " + e.getMessage());
                }
            } else {
                test.log(LogStatus.FAIL, "Login Header is not displayed for user: " + username);
            }

        } catch (Exception e) {
            test.log(LogStatus.ERROR, "Exception occurred for user: " + username + " - " + e.getMessage());
        } finally {
            test.log(LogStatus.INFO, "Moving on to the next credentials");
        }
    }

}
