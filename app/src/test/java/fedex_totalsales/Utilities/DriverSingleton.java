package fedex_totalsales.Utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverSingleton {

    private static WebDriver driver = null;

    private DriverSingleton(){}

    public static WebDriver getdriver() throws MalformedURLException{

        if(driver==null){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("createDriver()");
        }
        driver.manage().window().maximize();

        return driver;
    }
}