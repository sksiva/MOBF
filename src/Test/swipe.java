package Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class swipe {

	public static void main(String[] args) throws AWTException, InterruptedException 
	{
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Apple iPhone 6 Plus");
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		System.setProperty("webdriver.chrome.driver", "D:/SK Backups/Selenium/chromedriver.exe");
		WebDriver dr = new ChromeDriver(capabilities);
		dr.get("https://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
		
		WebDriverWait wait1 = new WebDriverWait(dr, 30);
		WebElement ele1 = wait1.until(ExpectedConditions.
						presenceOfElementLocated(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div")));
			  
		WebElement draggable = dr.findElement(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div"));
	    System.out.println("Location"+draggable.getLocation());
	    
	    //Robot Class
        Robot r = new Robot(); 
        Thread.sleep(4000); 
        r.mouseMove(30, 349);
        r.mousePress(InputEvent.BUTTON1_MASK); 
        //Thread.sleep(2000); 
        r.mouseMove(500, 350); 
        r.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
