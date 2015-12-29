package Test;

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

public class TestSample {
  public WebDriver dr;
  
  @BeforeTest
  public void beforeTest() {
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
	  String Str1 = W2.findElement(By.className("skMobff_ItemCount")).getText();
	  	  
	  System.out.println("***** Qty1 ***** ");
	  System.out.println(Str1);
	  W2.click();	  
	  Thread.sleep(3000);
	  WebElement WM1 = dr.findElement(By.className("skMobff_orderItems"));
	  List<WebElement> LL1 = dr.findElements(By.className("skMobff_productDetails"));
	  int i = LL1.size();
	  String Str2 = String.valueOf(i);
	  
	  //String Str2 = dr.findElement(By.id("skMobff_orderQty")).getText();
	  System.out.println("***** Qty2 ***** ");
	  System.out.println(Str2);
	  if(Str1.contains(Str2))
	  {
		  System.out.println("Order Items Qty are Same in PLP & PO Items page: "+Str1);
	  }
	  else
	  {
		  System.out.println("Qty in PLP is : "+Str1+" Qty in PO Items page : "+Str2);
	  }
  }
   
  @AfterTest
  public void afterTest() {
	  System.out.println("Scenario Completed Successfully");
	  //dr.quit();
  }
}