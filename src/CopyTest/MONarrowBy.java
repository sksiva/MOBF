package CopyTest;

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

public class MONarrowBy {
	public static WebDriver dr;
	public static WebElement WB1;
	public static String Str1, Str2, Str3;
	public static By orderAttr;
	public static WebElement element;
	

	public static void main(String[] args) throws Exception 
	{
		NARROWBY();

	}

	
	public static WebElement highlightElement(By by) throws Exception
	{
		element = dr.findElement(by);
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
	
	public static  void NARROWBY() throws Exception {
		
      	Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Apple iPhone 6 Plus");
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		System.setProperty("webdriver.chrome.driver", "D:/SK Backups/Selenium/chromedriver.exe");
		dr = new ChromeDriver(capabilities);
		
		dr.get("https://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
		  Thread.sleep(4000);
	      WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  	  highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[2]")).click();
	  	  
	  	  //Random NARROW BY Selections
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMob_filterOptions")));
		  WebElement WM1 = dr.findElement(By.className("skMob_filterOptions"));
		  List<WebElement> LWM1 = WM1.findElements(By.className("skMobff_filter "));
		  Random rand1 = new Random(System.currentTimeMillis());
		  WebElement W1 = LWM1.get(rand1.nextInt(LWM1.size()));
		  String Menu1 = W1.getText();
		  System.out.println("Menu1 : "+Menu1);
		  W1.click();
		  		  
		  //Random Order Selection After Filter
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_OrderMainList")));
		  WebElement WO1 = dr.findElement(By.className("skMobff_OrderMainList"));
		  List<WebElement> WP1 = WO1.findElements(By.className("skMobff_orders"));
		  Random rand = new Random(System.currentTimeMillis());
		  WebElement WK1 = WP1.get(rand.nextInt(WP1.size()));
		  System.out.println("***** Rand Val ***** ");
		  System.out.println(WK1.getText());
		  WK1.click();
		  
		  // Items Count Validation
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_pendingOrderItems")));
		  WebElement Q1 = dr.findElement(By.className("skMobff_pendingOrderItems"));
		  List<WebElement> LQ1 = Q1.findElements(By.className("skMobff_productDetails"));
		  int LQ = LQ1.size();
		  System.out.println("Size : "+LQ);
		  int flag=0;
		  if(Menu1!="Clear All")
		  {
				  for(int i=0;i<LQ;i++)
				  {
					  String Z1 = "//*[@id='id_skMobff_productDetails_";
					  String Z2 = "']/div[1]/div/div[2]";
					  String NM = dr.findElement(By.xpath(Z1+i+Z2)).getText();
					  System.out.println(i+" : "+NM);
					  
					  if(Menu1.equals(NM))
					  {
						  flag=1;
						  System.out.println("Correct Order is displayed for the selected Category : "+Menu1);
						  break;
					  }
				  }
				  if(flag==0)
				  {
					  System.out.println("In-correct Order is displayed for the selected Category "+Menu1);
				  }
		  }
		  else
		  {
			  System.out.println("All Items will be displayed while selecting "+Menu1);
		  }
		  
		// Back to PO
		  Thread.sleep(4000);
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();		
	}
}