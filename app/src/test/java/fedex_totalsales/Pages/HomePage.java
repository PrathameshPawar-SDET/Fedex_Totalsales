package fedex_totalsales.Pages;

import fedex_totalsales.Utilities.Wrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import javax.xml.xpath.XPath;

public class HomePage {
    WebDriver driver;

    public  HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
    }

    String homeURL = "https://fedex-staging.totalsales.com/";

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

    @FindBy(className = "input-group-addon")
    private WebElement SearchButton;

    @FindBy(xpath="//a[text()='HELP ME']")
    private WebElement helpMe;

    @FindBy(xpath = "//a[text()='VIEW CART']")
    private WebElement viewCart;

    @FindBy(xpath = "//a[normalize-space()='My Account']")
    private WebElement myAccount;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement logout;

    @FindBy(xpath = "//p[contains(text(), 'Employee Number:')]")
    private WebElement Employee_text;

    public void navigationToHome(){
        Wrapper.navigate(driver,homeURL);
    }

    public void searchProduct(String Product) throws InterruptedException{
        Search.clear();
        Thread.sleep(3000);
        Wrapper.sendKeys(driver, this.Search, Product);
    }

    public void SearchButton(){
        Wrapper.click(this.SearchButton, driver);
    }

    public void helpme(){
        Wrapper.click(this.helpMe,driver);
    }

    public void navigatetoSALogin(){
        Wrapper.click(this.SALogin, driver);
    }

    public boolean verifylogin(){
        return this.Employee_text.isDisplayed();
    }

    public void logout(){
        this.myAccount.click();
        this.logout.click();
    }
















}
