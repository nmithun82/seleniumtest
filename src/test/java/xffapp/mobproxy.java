package xffapp;

/**
 * Created by mllyod001c on 10/20/2016.
 */

import java.io.File;
import java.io.IOException;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class mobproxy {

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.firefox.marionette","C:\\seleinuminfo\\firefox driver\\geckodriver.exe");

        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start();

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);


        FirefoxProfile profile = new FirefoxProfile();


        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("network.proxy.http", "localhost");
        profile.setPreference("network.proxy.http_port", 8080);
        profile.setPreference("network.proxy.ssl", "localhost");
        profile.setPreference("network.proxy.ssl_port", 443);

        WebDriver driver = new FirefoxDriver(capabilities);
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        proxy.newHar("yahoo.com");
        driver.get("https://login.comcast.net/login?r=comcast.net&s=oauth&continue=https%3A%2F%2Flogin.comcast.net%2Foauth%2Fauthorize%3Fclient_id%3Dxtv-account-selector%26redirect_uri%3Dhttps%3A%2F%2Fxtv-pil.xfinity.com%2Fxtv-authn%2Fxfinity-cb%26response_type%3Dcode%26scope%3Dopenid%2520https%3A%2F%2Flogin.comcast.net%2Fapi%2Flogin%26state%3Dhttps%3A%2F%2Ftv.xfinity.com%2Fpartner-success.html%26prompt%3Dlogin%26response%3D1&reqId=1891f0c3-c1de-4e32-8c31-e159cdd62d22&forceAuthn=1&client_id=xtv-account-selector");

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


        Har har = proxy.getHar();
        // System.out.println(har.toString());
        String strfilepath="C:\\seleinuminfo\\perf.har";
        FileOutputStream fos = new FileOutputStream(strfilepath);
        har.writeTo(fos);

        //server.stop();
        //driver.quit();
    }
}


