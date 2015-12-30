package Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

public class NewSam {
	public WebDriver dr;	
  @BeforeTest
  public void beforeTest() {
	  //Mobile Emulator in Chrome
	  ChromeOptions op=new ChromeOptions();
      op.addArguments("--disable-popup-blocking");
      op.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
      //Reporter.log("User agent set as iphone!",bolConsole);
      System.setProperty("webdriver.chrome.driver","D:/SK Backups/Selenium/chromedriver.exe");
      dr = new ChromeDriver(op);
  }
  
  @Test (priority = '2')
  public void ScrollOrder() throws InterruptedException {
	  
	 dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
	  Thread.sleep(4000);
	  for (int second = 0;; second++) {
		    if(second >=10){
		        break;
		    }
		    ((JavascriptExecutor) dr).executeScript("window.scrollBy(0,1500)", "");
		    Thread.sleep(3000);
		}
	  
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  System.out.println("Scroll Completed "+"&"+" Number of Orders are : "+WB1.size());
  }
  
  @Test (enabled=true)
  public void ViewOrder() throws InterruptedException {
	  Thread.sleep(3000);
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  System.out.println("***** Rand Val ***** ");
	  System.out.println(W2.getText());
	  W2.click();
	 
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	//Go to Item Details page	
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("//skMobff_ItemProductsDiv")));
	  dr.findElement(By.className("//skMobff_ItemProductsDiv")).click();
	  
	  
	  
	  /*	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.id("id_skMobff_productDetails_0")));
	  dr.findElement(By.id("id_skMobff_productDetails_0")).click();*/
	  
	  
	// Back to PO Items
	  WebElement ele2 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	// Back to PO
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();	  
  }

  @AfterTest
  public void afterTest() {
  }

}
