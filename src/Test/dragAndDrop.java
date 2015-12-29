package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class dragAndDrop {
    WebDriver driver;
  @Test
  public void f() throws InterruptedException {
      ChromeOptions op=new ChromeOptions();
      op.addArguments("--disable-popup-blocking");
      op.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
      //Reporter.log("User agent set as iphone!",bolConsole);
       System.setProperty("webdriver.chrome.driver","D:/SK Backups/Selenium/chromedriver.exe");
       driver = new ChromeDriver(op);
       String URL = "http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/mobileFulfillment";
       driver.get(URL);
       Thread.sleep(6000);
     WebElement draggable = driver.findElement(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div"));
      System.out.println("Location"+draggable.getLocation());
     System.out.println("Window size:"+ driver.manage().window().getSize().getWidth());
     int dragg=driver.manage().window().getSize().getWidth();
     int positive=dragg/2+1;
      new Actions(driver).dragAndDropBy(draggable, positive,0).build().perform();
      
      
      
      
     /* Actions actions = new Actions(driver);
      WebElement mainMenu = driver.findElement(By.xpath("//*[@id='id_skMobff_ordersList_1']"));
      actions.moveToElement(mainMenu);
      new Actions(driver).dragAndDropBy(mainMenu, 800,0).build()
      .perform();*/
      
  }
}
