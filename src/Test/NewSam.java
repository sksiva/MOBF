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
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class NewSam {
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
  
  @Test
  public void f() throws InterruptedException {
	  dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/mobilefulfillment");
	  Thread.sleep(20000);
	  WebElement draggable = dr.findElement(By.xpath("//*[@id='id_skMobff_ordersList_0']/div[3]/div/div/div[1]/img"));
	  System.out.println("Location"+draggable.getLocation());
	  System.out.println("Window size:"+ dr.manage().window().getSize().getWidth());
	  int dragg=dr.manage().window().getSize().getWidth();
	  //int positive=dragg/2+1;
	  new Actions(dr).dragAndDropBy(draggable, 527,0).build()
	  .perform();
	  
	  
	 /* WebElement draggable = dr.findElement(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div"));
	  System.out.println("Location"+draggable.getLocation());
	  System.out.println("Win Size: "+dr.manage().window().getSize().getWidth());
	  new Actions(dr).dragAndDropBy(draggable, 800, 0).build().perform();*/
	  

	  
	  
  }

  @AfterTest
  public void afterTest() {
  }

}
