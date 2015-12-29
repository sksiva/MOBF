package pak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MOBF_PO {
  public WebDriver dr;
  
  @BeforeTest
  public void beforeTest() {
	  //Mobile Emulator in Chrome
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Apple iPhone 6 Plus");
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		System.setProperty("webdriver.chrome.driver", "D:/SK Backups/Selenium/chromedriver.exe");
		dr = new ChromeDriver(capabilities);		
  }

  @Test (priority = '1')
  public void ScrollOrder() throws InterruptedException {
	  
	  dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
	  
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
  
  @Test (priority = '2')
  public void ViewOrder() throws InterruptedException {
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
				presenceOfElementLocated(By.id("id_skMobff_productDetails_0")));
	  dr.findElement(By.id("id_skMobff_productDetails_0")).click();
	// Back to PO Items
	  WebElement ele2 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	// Back to PO
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();	  
  }
  
  @Test (priority = '3')
  public void UrgentOrder() {
	  
	  if(dr.findElements(By.xpath("//*[@id='id_skMobff_ordersList_0']/div[4]")).size() != 0){
		  System.out.println("Urgent Order");
		  }
	  else{
		  System.out.println("Not An Urgent Order");
		  }
  }
  
  @Test (enabled = false)
  public void TestOrder1() throws InterruptedException {
	  		
	    }
  
  @Test (priority = '4')
  public void TestOrder2() throws InterruptedException {
	  
  }
  
  @AfterTest
  public void afterTest() {
	  System.out.println("Scenario Completed Successfully");
	  //dr.quit();
  }
}