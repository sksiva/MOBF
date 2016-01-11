package Test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Delete {
	public WebDriver dr;
	public WebElement WB1;
	public String Str1, Str2, Str3;
	public By orderAttr;
		
  @BeforeTest
  public void beforeTest() {
	  //Mobile Emulator in Chrome
	  ChromeOptions op=new ChromeOptions();
      op.addArguments("--disable-popup-blocking");
      op.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
      System.setProperty("webdriver.chrome.driver","D:/SK Backups/Selenium/chromedriver.exe");
      dr = new ChromeDriver(op);
  }
  
  public WebElement highlightElement(By by) throws Exception
	{
		WebElement element = dr.findElement(by);
		// Draw a border around the found element
		if (dr instanceof JavascriptExecutor)
		{
			JavascriptExecutor js = (JavascriptExecutor) dr;
			js.executeScript("arguments[0].style.border='2px solid red'", element);
			Thread.sleep(2000);
			js.executeScript("arguments[0].style.border=''", element);
		}
		return element;
	}
  
  @Test (priority = 1)
  public void GetOrder() throws InterruptedException {
	  
	  dr.get("https://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
	  Thread.sleep(5000);
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));	
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	 
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  
	  //Order's Xpath using OrderID
	  Str1 = ".//*[@orderid='";
	  Str2 = W2.findElement(By.className("skMobff_OrderId")).getText();
	  Str3 = "']";
	  System.out.println("Selected Order Number To Be Manipulate : "+Str2);
	  System.out.println("--------------------------------------------------------------");
	  orderAttr = By.xpath(Str1+Str2+Str3);
	  	  
  }
  
  @Test (priority = 2)
  public void ViewOrder() throws Exception {
	  
	  	dr.findElement(orderAttr).click();
	  	WebDriverWait wait1 = new WebDriverWait(dr, 30);
		//Go to Item Details page
		  WebElement ele1 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.id("id_skMobff_productDetails_0")));
		  highlightElement(By.id("id_skMobff_productDetails_0")).click();
		// Back to PO Items
		  Thread.sleep(5000);
		  WebElement ele2 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
		// Back to PO
		  WebElement ele3 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
		  System.out.println("Order "+Str2+" Viewed Successfully");
		  System.out.println("--------------------------------------------------------------");
  }

  @AfterTest
  public void afterTest() {
	  //dr.quit();
  }

}
