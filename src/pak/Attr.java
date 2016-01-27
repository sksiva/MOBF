package pak;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class Attr {
	
	public static WebDriver dr;
	public static WebElement WB1;
	public static String Str1, Str2, Str3;
	public static By orderAttr;
	public static WebElement element;
	
	@BeforeSuite
	public static void SOPO() {
	      
	      	Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Apple iPhone 6 Plus");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			System.setProperty("webdriver.chrome.driver", "D:/SK Backups/Selenium/chromedriver.exe");
			dr = new ChromeDriver(capabilities);
			
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
}