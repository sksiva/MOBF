package pak;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MOBFArchiveOrder extends Attributes{
	  @Test
 public void REVERSESWIPE() throws Exception {
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
		  wait1.until(ExpectedConditions.presenceOfElementLocated(orderAttr));
		  WebElement draggable1 = dr.findElement(orderAttr);
		  System.out.println("Swiped Order Numb : "+draggable1.findElement(By.className("skMobff_OrderId")).getText());
		  
		  WebElement FW = dr.findElement(By.id("id_skMobff_ordersList_0"));
		  int FY = FW.getLocation().getY()+150;
		    
		  //X-Axis From
		  int FX1 = FW.getLocation().getX()+25;
		    
		  //X-Axis To
		  int FX2 = FW.getLocation().getX()+240;
			
		  //Scroll Till Order Visibility
		  ((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView(true);", draggable1);
		    
		  //Swipe Using Robot Class
	      Robot r = new Robot(); 
	      Thread.sleep(4000);
	      dr.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	      r.mouseMove(FX2, FY);
	      r.mousePress(InputEvent.BUTTON1_MASK); 
	      r.mouseMove(FX1, FY); 
	      r.mouseRelease(InputEvent.BUTTON1_MASK);
	      highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
		  System.out.println("--------------------------------------------------------------");
	  }
}
