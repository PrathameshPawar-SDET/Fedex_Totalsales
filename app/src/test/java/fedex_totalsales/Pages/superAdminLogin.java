package fedex_totalsales.Pages;

import fedex_totalsales.Utilities.Wrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class superAdminLogin {

    WebDriver driver;

    public superAdminLogin(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//h3/b[text()='Super Admin Login']")
    private WebElement SALoginHeader;

    @FindBy(id = "username")
    private WebElement Username;

    @FindBy(id="password")
    private WebElement Password;

    @FindBy(xpath = "//button[normalize-space()='SUBMIT']")
    private WebElement SubmitButton;

    @FindBy(className = "help-block")
    private WebElement errorMessage;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    public boolean isheaderdisplayed() {
        wait.until(ExpectedConditions.visibilityOf(SALoginHeader));
        return this.SALoginHeader.isDisplayed();
    }

    public void enterCredentials(String username, String password){
        Wrapper.sendKeys(driver,this.Username, username);
        Wrapper.sendKeys(driver, this.Password, password);
    }

    public void Login(){
        Wrapper.click(this.SubmitButton, driver);
    }

    public String geterror(){
        return this.errorMessage.getText();
    }



}
