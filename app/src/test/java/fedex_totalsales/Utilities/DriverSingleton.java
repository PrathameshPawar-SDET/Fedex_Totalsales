package fedex_totalsales.Utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverSingleton {

    private static WebDriver driver = null;

    private DriverSingleton(){}

    public static WebDriver getdriver() throws MalformedURLException{

        if(driver==null){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
//            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");


//            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

            driver = new ChromeDriver(options);
            System.out.println("Creating new ChromeDriver instance.");
        }
//        driver.manage().window().maximize();

        return driver;
    }
}