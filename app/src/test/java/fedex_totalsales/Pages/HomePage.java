package fedex_totalsales.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    WebDriver driver;

    public  HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    @FindBy(xpath = "//a[text()='HOME']")
    private  WebElement Home;

    @FindBy(xpath = "//a[text()='AWARD LEVELS ']")
    private WebElement Award_level;

    @FindBy(xpath = "//li/a[text()='BRONZE']")
    private WebElement Bronze;

    @FindBy(xpath = "//li/a[text()='SILVER']")
    private WebElement Silver;

    @FindBy(xpath = "//li/a[text()='GOLD']")
    private WebElement Gold;

    @FindBy(xpath = "//li/a[text()='TITANIUM']")
    private WebElement Titanium;

    @FindBy(xpath = "//li/a[text()='PLATINUM']")
    private WebElement Platinum;

    @FindBy(xpath = "//a[text()='CATEGORIES ']")
    private WebElement Categories;

    @FindBy(xpath = "//a[text()='FAQ']")
    private WebElement Faq;

    @FindBy(xpath = "//a[text()='QRP Support ']")
    private WebElement Support;

    @FindBy(xpath = "//a[text()='Login']")
    private WebElement Login;

    @FindBy(xpath = "//a[text()='Super Admin Login']")
    private WebElement SALogin;

    @FindBy(xpath = "//input[@placeholder='SEARCH']")
    private WebElement Search;







}
