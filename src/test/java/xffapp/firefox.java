package xffapp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import org.openqa.selenium.firefox.*;
import java.util.concurrent.*;

/**
 * Created by mllyod001c on 10/13/2016.
 */
public class firefox {

    public static void main(String[] args)
    {
        System.setProperty("webdriver.firefox.marionette","C:\\seleinuminfo\\firefox driver\\geckodriver.exe");
        WebDriver driver=new FirefoxDriver();
        driver.get("https://login.comcast.net/login?r=comcast.net&s=oauth&continue=https%3A%2F%2Flogin.comcast.net%2Foauth%2Fauthorize%3Fclient_id%3Dxtv-account-selector%26redirect_uri%3Dhttps%3A%2F%2Fxtv-pil.xfinity.com%2Fxtv-authn%2Fxfinity-cb%26response_type%3Dcode%26scope%3Dopenid%2520https%3A%2F%2Flogin.comcast.net%2Fapi%2Flogin%26state%3Dhttps%3A%2F%2Ftv.xfinity.com%2Fpartner-success.html%26prompt%3Dlogin%26response%3D1&reqId=1891f0c3-c1de-4e32-8c31-e159cdd62d22&forceAuthn=1&client_id=xtv-account-selector");
        //driver.get("https://tv.xfinity.com/listings");
        WebElement element = driver.findElement(By.id("user"));
        element.sendKeys("cdvr_den1");

        //wait 5 secs for  userid to be entered
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Enter Password
        WebElement element1 = driver.findElement(By.id("passwd"));
        element1.sendKeys("Xfinity1");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        element.submit();

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id='content']/button[1]")).getAttribute("btn1");

        driver.findElement(By.xpath("//*[@id='content']/button[2]")).getAttribute("btn2");
        driver.findElement(By.xpath("//*[@id='content']/button[2]")).click();

        WebElement element2 = driver.findElement(By.id("deviceName"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        element2.sendKeys("Testacc01");

        WebElement element3 = driver.findElement(By.id("next"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        element3.click();

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        driver.get("https://tv.xfinity.com/listings");


       /* if (driver.findElement(By.xpath("//*[@id='polaris-header-main-navigation-authenticated-myaccount']/span")).getText()=="My Account")
        {
            System.out.print("Test passed - Login Successfull");
    }
        //*[@id='polaris-header']/div/ul[1]/li[8] */


        //element.submit();
        //Submit button



    }
}

