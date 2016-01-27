package Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Delete {
	
	public static WebDriver dr;
	public static WebElement WB1;
	public static String Str1, Str2, Str3;
	public static By orderAttr;
	public static WebElement element;

	public static void main(String[] args) throws InterruptedException
	{
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Apple iPhone 6 Plus");
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		System.setProperty("webdriver.chrome.driver", "D:/SK Backups/Selenium/chromedriver.exe");
		dr = new ChromeDriver(capabilities);
		
		dr.get("https://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment#pendingOrder");
		Thread.sleep(4000);
		WebDriverWait wait1 = new WebDriverWait(dr, 30);


	  	dr.findElement((By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[2]"))).click();
	  	Thread.sleep(4000);
	  	  //Random NARROW BY Selections
		  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("skMob_filterOptions")));
		  WebElement WM1 = dr.findElement(By.className("skMob_filterOptions"));
		  List<WebElement> LWM1 = WM1.findElements(By.className("skMobff_filter "));
		  Random rand1 = new Random(System.currentTimeMillis());
		  WebElement W1 = LWM1.get(rand1.nextInt(LWM1.size()));
		  String M1 = W1.getText();
		  System.out.println("M1 : "+M1);
		  W1.click();
		  		  
		  //Random Order Selection After Filter
		  WebElement ele2 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.className("skMobff_OrderMainList")));
		  WebElement WO1 = dr.findElement(By.className("skMobff_OrderMainList"));
		  List<WebElement> WP1 = WO1.findElements(By.className("skMobff_orders"));
		  Random rand = new Random(System.currentTimeMillis());
		  WebElement WK1 = WP1.get(rand.nextInt(WP1.size()));
		  System.out.println("***** Rand Val ***** ");
		  System.out.println(WK1.getText());
		  WK1.click();
		  
		  //Ranomly Check an Item's Category
		  WebElement ele3 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.className("skMobff_orderItems")));
		  WebElement X1 = dr.findElement(By.className("skMobff_orderItems"));
		  List<WebElement> LX1 = X1.findElements(By.className("skMobff_productDetails "));
		  Random rand2 = new Random(System.currentTimeMillis());
		  WebElement WX1 = LX1.get(rand1.nextInt(LX1.size()));
		  String M2 = WX1.findElement(By.className("skMobff_Value")).getText();
		  System.out.println("M2 Valueeeeeeeeeeeeee: "+M2);
		  
		  if(M1!="Clear All"){
			  if(M1.contains(M2)){
				  System.out.println("Success ! ! ! Correct Orders are displayed for the selected Category");
			  }
				  else{
					  System.out.println("Sorry ! ! ! Incorrect Orders are displayed for the selected Category");
				  }	
		  }
		  else{
			  System.out.println("OOPS ! ! ! System Randomly Selected Clear All option from NARROW BY");			  
		  }
		  
		  // Back to PO
		  WebDriverWait wait2 = new WebDriverWait(dr, 30);
		  WebElement ele4 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  dr.findElement((By.xpath(".//*[@class='skMobff_backBtnIcon']"))).click();
	}

}
