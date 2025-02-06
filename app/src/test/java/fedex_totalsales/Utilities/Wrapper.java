package fedex_totalsales.Utilities;

import javax.swing.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wrapper {
    WebDriver driver;

    public static boolean click(WebElement elementToClick, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(elementToClick));

            Actions act = new Actions(driver);
            act.moveToElement(elementToClick).perform();

//            Thread.sleep(500);

            act.click(elementToClick).perform();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                elementToClick.click();
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public static  boolean sendKeys(WebDriver driver, WebElement inputBox, String keysToSend){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(inputBox));
        try{if(inputBox.isDisplayed()){
            inputBox.sendKeys(keysToSend);
            return true;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }

    public static  boolean navigate(WebDriver driver , String url){
        boolean status;
        status = driver.getCurrentUrl().equals(url);
        if(!status){
            driver.navigate().to(url);
        }else{
            return status;
        }
        return status;
    }

    public static WebElement findElementWithRetry(WebDriver driver , By by , int retryCount ){
        WebElement element = null;
        for(int i =0 ;i<retryCount;i++){
            element = driver.findElement(by);
        }
        return element;


    }


}