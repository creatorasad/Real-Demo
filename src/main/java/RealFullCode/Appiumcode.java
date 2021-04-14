package RealFullCode;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Appiumcode {
  public String username = "<LT username>";
  public String accesskey = "<LT key>";
  public RemoteWebDriver driver;
  public String gridURL = "@hub.lambdatest.com/wd/hub";
  SessionId session;

  @BeforeTest
  @org.testng.annotations.Parameters(value = { "device", "version", "platform", "fixedIp" })
  public void setUp(String device, String version, String platform, String fixedIp) throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", platform);
    capabilities.setCapability("deviceName", device);
    capabilities.setCapability("platformVersion", version);
    capabilities.setCapability("build", "demo");
    capabilities.setCapability("name", "device");
    capabilities.setCapability("isRealMobile", true); // To Real Device
    capabilities.setCapability("network", true); // To enable network logs
    capabilities.setCapability("visual", true); // To enable step by step screenshot
    capabilities.setCapability("video", true); // To enable video recording
    capabilities.setCapability("console", true); // To capture console logs

    try {
      StopWatch driverStart = new StopWatch();
      driverStart.start();
      String url = "https://" + username + ":" + accesskey + gridURL;
      System.out.println(url);
      driver = new RemoteWebDriver(new URL(url), capabilities);
      driverStart.stop();
      float timeElapsed = driverStart.getTime() / 1000f;
      System.out.println("Driver Setup time in Seconds " + "  " + timeElapsed + "Sec.");

    } catch (MalformedURLException e) {
      System.out.println("Invalid grid URL");
    } catch (Exception f) {
      System.out.println(f.getMessage());
    }
  }

  @Test(enabled = true)
  public void AddDelete() throws Exception {

    driver.get("http://the-internet.herokuapp.com/");
    Thread.sleep(2000);
    WebElement link = driver.findElement(By.cssSelector("li:nth-child(2) > a"));
    link.click();
    Thread.sleep(2000);

    WebElement add = driver.findElement(By.xpath("//button[normalize-space()='Add Element']"));
    add.click();
    Thread.sleep(2000);

    WebElement delete = driver.findElement(By.xpath("//button[normalize-space()='Delete']"));
    add.click();
    Thread.sleep(2000);
    System.out.println("Deleted sucessfully");

  }

  @Test(enabled = true)
  public void drag() throws Exception {

    driver.get("http://the-internet.herokuapp.com/drag_and_drop");

    // Element which needs to drag.
    WebElement From = driver.findElement(By.xpath("//div[@id='column-a']"));
    Thread.sleep(2000);

    // Element on which need to drop.
    WebElement To = driver.findElement(By.xpath("//div[@id='column-b']"));
    Thread.sleep(2000);

    // Using Action class for drag and drop.
    Actions act = new Actions(driver);
    Thread.sleep(2000);

    // Dragged and dropped.
    act.dragAndDrop(From, To).build().perform();
    Thread.sleep(2000);

    System.out.println("dropped sucessfully");

  }

  @Test(enabled = true)
  public void dropdown() throws Exception {

    driver.get("http://the-internet.herokuapp.com/dropdown");

    // Element which needs to drag.
    Select select = new Select(driver.findElement(By.xpath("//select[@id='dropdown']")));
    Thread.sleep(2000);
    select.selectByVisibleText("Option 1");
    Thread.sleep(2000);
    System.out.println("selected sucessfully");

  }

  @Test(enabled = true)
  public void Site() throws Exception {

    driver.get("https://www.tesla.com/");
    Thread.sleep(5000);
    driver.get("https://www.lambdatest.com/");
    Thread.sleep(5000);
    driver.get("https://www.amazon.com/");
    Thread.sleep(5000);
    driver.get("https://whatismyipaddress.com/");
    Thread.sleep(5000);

    System.out.println("site load sucessfully");

  }

  @Test(enabled = true)
  public void maxi() throws Exception {

    driver.get("https://www.google.com/");

    Thread.sleep(5000);
    System.out.println("google sucessfully");

  }

  @AfterTest
  public void tearDown() throws Exception {
    long quitetimestart;
    long quitetimestop;
    quitetimestart = System.currentTimeMillis();
    driver.quit();
    quitetimestop = System.currentTimeMillis();
    quitetimestop = quitetimestop - quitetimestart;
    System.out.println("Driver Quite time" + " " + quitetimestop / 1000f + "Sec.");
  }
}
