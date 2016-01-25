package Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class location {

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
		
		//Get First Element's Position (X, Y)
		Thread.sleep(5000);
		WebElement FW = dr.findElement(By.id("id_skMobff_ordersList_0"));
		int FX = FW.getLocation().getX();
	    int FY = FW.getLocation().getY()+150;
	    
	    //X-Axis From
	    int FX1 = FW.getLocation().getX()+25;
	    
	    //X-Axis To
	    int FX2 = FW.getLocation().getX()+240;
		//Thread.sleep(5000);
		WebDriverWait wait1 = new WebDriverWait(dr, 60);
		WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("skMobff_OrderMainList")));
		WebElement A1 = dr.findElement(By.className("skMobff_OrderMainList"));
		List<WebElement> LA1 = A1.findElements(By.className("skMobff_orders"));
		
		//Random Order Selection for Swipe
		Random rand = new Random(System.currentTimeMillis());
		WebElement W2 = LA1.get(rand.nextInt(LA1.size()));
		String SMS = W2.getAttribute("id");
		System.out.println("Attr : "+SMS);
		
		//Swipe
		//WebDriverWait wait1 = new WebDriverWait(dr, 60);
		/*WebElement ele1 = wait1.until(ExpectedConditions.
						presenceOfElementLocated(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div")));*/
		
		WebElement ele2 = wait1.until(ExpectedConditions.presenceOfElementLocated(By.id(SMS)));
		WebElement draggable = dr.findElement(By.id(SMS));
		System.out.println("Order Numb : "+draggable.findElement(By.className("skMobff_OrderId")).getText());
		
	    //Scroll Till Order Visibility
		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);", draggable);
	    
	    
	    //Swipe Using Robot Class
        Robot r = new Robot(); 
        Thread.sleep(4000);
        dr.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        r.mouseMove(FX1, FY);
        r.mousePress(InputEvent.BUTTON1_MASK); 
        r.mouseMove(FX2, FY); 
        r.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
