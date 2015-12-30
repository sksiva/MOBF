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
  
  @Test (priority = '1')
  public void ScrollOrder() throws InterruptedException {
	  
	 dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
  }
  
  @Test (priority = '2')
  public void NARROW() throws InterruptedException {
	  Thread.sleep(12000);
	  dr.findElement(By.className("skMobff_filterBtnText")).click();
	  Thread.sleep(3000);
	  
	  // Random NARROW BY Selections
	  WebElement WM1 = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[1]/div[3]"));
	  List<WebElement> LWM1 = WM1.findElements(By.className("skMobff_filter "));
	  
	  Random rand1 = new Random(System.currentTimeMillis());
	  WebElement W1 = LWM1.get(rand1.nextInt(LWM1.size()));
	  String M1 = W1.getText();
	  System.out.println("M1 Valueeeeeeeeee: "+M1);
	  W1.click();
	  
	  //Random Order Selection After Filter
	  Thread.sleep(4000);
	  WebElement WO1 = dr.findElement(By.className("skMobff_OrderMainList"));
	  List<WebElement> WP1 = WO1.findElements(By.className("skMobff_orders"));
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement WK1 = WP1.get(rand.nextInt(WP1.size()));
	  System.out.println("***** Rand Val ***** ");
	  System.out.println(WK1.getText());
	  WK1.click();
	  
	  //Ranomly Check an Item's Category
	  Thread.sleep(4000);
	  WebElement X1 = dr.findElement(By.className("skMobff_orderItems"));
	  List<WebElement> LX1 = X1.findElements(By.className("skMobff_productDetails "));
	  
	  Random rand2 = new Random(System.currentTimeMillis());
	  WebElement WX1 = LX1.get(rand1.nextInt(LX1.size()));
	  String M2 = WX1.findElement(By.className("skMobff_Value")).getText();
	  System.out.println("M2 Valueeeeeeeeeeeeee: "+M2);
	  
	  if(M1.contains(M2))
	  {
		  System.out.println("Success ! ! ! Correct Orders are displayed for the selected Category");
	  }
	  else
	  {
		  System.out.println("Sorry ! ! ! Incorrect Orders are displayed for the selected Category");
	  }
	  
	  
/*	  String M2 = WX1.getText();
	  System.out.println("Option Name: "+M2);
	  W1.click();*/
  }

  @AfterTest
  public void afterTest() {
  }

}
