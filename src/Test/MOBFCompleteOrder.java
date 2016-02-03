package Test;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MOBFCompleteOrder extends Attributes{
		
	  @Test (priority = 1)
	  public void OrderCountPO() throws Exception {
		  
		  //Value from CO Icon
		  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
		  String POcount = highlightElement(By.id("id_menuListItemCount_2")).getText();
		  System.out.println("COcount Icon : "+POcount);
		  highlightElement(By.name("completedOrder")).click();
		  
		  // Get Number of Orders count from Complete Orders page
		  Thread.sleep(3000);
		  WebElement W1 = highlightElement(By.className("skMobff_OrderMainList"));
		  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
		  int X1 = WB1.size();
		  String numOrders = Integer.toString(X1);
		  System.out.println("COcount Items : "+numOrders);
		  
		  if(numOrders.equals(POcount))
		  {
			  System.out.println("Order Count in Complete Order page is same as in Complete Order icon count : "+numOrders);
		  }
		  else
		  {
			  System.out.println("Order Count in CO screen is : "+numOrders+" CO Icon count is : "+POcount);
		  }
		  
		  System.out.println("--------------------------------------------------------------");
	  }
	  
	  @Test (priority = 2)
	  public void SWIPE() throws Exception {
		  
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
	      r.mouseMove(FX1, FY);
	      r.mousePress(InputEvent.BUTTON1_MASK); 
	      r.mouseMove(FX2, FY); 
	      r.mouseRelease(InputEvent.BUTTON1_MASK);
	      highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
		  highlightElement(By.name("archiveOrder")).click();
		  System.out.println("--------------------------------------------------------------");
	  }
}
