package Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class POSwipe {
	public WebDriver dr;	
  @BeforeTest
  public void beforeTest() {
	  //Mobile Emulator in Chrome
	  ChromeOptions op=new ChromeOptions();
      op.addArguments("--disable-popup-blocking");
      op.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
      System.setProperty("webdriver.chrome.driver","D:/SK Backups/Selenium/chromedriver.exe");
      dr = new ChromeDriver(op);
  }
  
  @Test
  public void swipePO() throws InterruptedException 
  {
	  dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/mobilefulfillment");
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WebElement ele1 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div")));
		  
	  WebElement draggable = dr.findElement(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div"));
      System.out.println("Location"+draggable.getLocation());
      System.out.println("Window size:"+ dr.manage().window().getSize().getWidth());
      int dragg=dr.manage().window().getSize().getWidth();
      System.out.println("dragg : "+ dragg);
      int positive=dragg/2+1;
      new Actions(dr).dragAndDropBy(draggable, positive,0).build().perform();
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Swipe Scenario Completed Successfully");
  }

}
