/**
 * 
 */
package org.test.utils;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.utils.driver.Driver;

import com.thoughtworks.gauge.Step;

import java.util.logging.Logger;

/**
 * @author karim
 *
 */
public class Web {
    public static String APP_URL = System.getenv("APP_URL");
    private final int DEFAULT_WEB_ELEMENT_TIMEOUT = 10;
    private int waitTime = Integer.parseInt(System.getenv("waitTime"));
    private final static Logger logger = Logger.getLogger(Web.class.getCanonicalName());

    @Step("Go to webapppoc website")
    public void launchTheApplication() {

        Driver.webDriver.get(APP_URL);
    }
    
   //As the user search for "JANINE BURKE"
   // * Then verify that the title is "Savage secrets"
    
    @Step("verify welcome message")
    public void searchFor() {
    	 WebDriverWait wait = new WebDriverWait(Driver.webDriver, DEFAULT_WEB_ELEMENT_TIMEOUT);
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));
    	
        WebElement welcome = findElementBy(By.id("msg"));
        Assert.assertEquals("Message : Welcome", welcome.getText());
        
    }
    
    @Step("verify the counter is an integer")
    public void verifyInt() {
    	 WebElement ele = Driver.webDriver.findElement(By.id("ctr"));
         String[] counter = ele.getText().split(":");
       try {
            int ctr = Integer.parseInt(counter[1].trim());
            Assert.assertNotNull(ctr);
            Assert.assertEquals("Counter", counter[0].trim());
        } catch (NumberFormatException e) {
            Assert.fail();

        }

    }


    @Step("verify the date")
    public  void verifyDate(){
        WebElement date = Driver.webDriver.findElement(By.id("jDate"));
        LocalDate localDateTime = new LocalDate();
        Assert.assertEquals("Local Date : "+localDateTime.toString(), date.getText());

    }
    private WebElement findElementBy(By by) {
        return Driver.webDriver.findElement(by);
    }
    public void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ie) {
            System.err.println("The sleep function was interrupted" + ie.toString());
        }
    }
}
