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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

  @Test (enabled=false)
  public void ScrollOrder() throws InterruptedException {
	  dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobilefulfillmenttest");
	  //dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobilefulfillment");
	  
	  for (int second = 0;; second++) {
		    if(second >=20){
		        break;
		    }
		    ((JavascriptExecutor) dr).executeScript("window.scrollBy(0,1500)", "");
		    Thread.sleep(3000);
		}
	  
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  System.out.println("Scroll Completed "+"&"+" Number of Orders are : "+WB1.size());
  }
    
  @Test (enabled=false)
  public void ViewOrder() throws InterruptedException {
	  
	  dr.findElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  Thread.sleep(3000);
	  dr.findElement(By.name("pendingOrder")).click();
	  Thread.sleep(3000);
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));	
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  String Str1 = W2.findElement(By.className("skMobff_OrderDate")).getText();
	  //String Str1 = W2.getText();
	  
	  System.out.println("***** Str1 ***** ");
	  System.out.println(Str1);
	  W2.click();
	  
	  Thread.sleep(3000);
	  String Str2 = dr.findElement(By.id("skMobff_orderDate")).getText();
	  System.out.println("***** Str2 ***** ");
	  System.out.println(Str2);
	  if(Str1.contains(Str2))
	  {
		  System.out.println("Your Selected Order's Date is SAME : "+Str1);
	  }
	  else
	  {
		  System.out.println("Order's Date in PLP page is: "+Str1+" Order's Date in Pending Order Item page is : "+Str2);
	  }
  }
   
  @Test (priority = '1')
  public void Narrow() throws InterruptedException {

	  dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobilefulfillmenttest");
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WebElement ele1 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.className("skMobff_filterBtnText")));
	  
	  dr.findElement(By.className("skMobff_filterBtnText")).click();
	  WebElement WS1 = dr.findElement(By.className("skMob_filterOptions  filter"));
	  List<WebElement> LWS1 = WS1.findElements(By.className("skMobff_filter"));
	  System.out.println("Size: "+LWS1);
  }
  
  @AfterTest
  public void afterTest() {
	  System.out.println("Scenario Completed Successfully");
	  //dr.quit();
  }
}