package pak;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MOBFMyOrder extends Attributes{
	
	public static WebElement Q1;
	public static List<WebElement> LQ1;
	
	  @BeforeClass
	  public void Del1() throws Exception {
		System.out.println("************************* My Orders *************************");
	  }
	  
	  @Test (priority=1)
	  public void SORTBY() throws Exception {
		  	WebDriverWait wait1 = new WebDriverWait(dr, 40);
		  	Thread.sleep(4000);
		  	wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_sortLabel")));
		  	highlightElement(By.className("skMobff_sortLabel")).click();
		  	WebElement sort = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[2]/select"));
		  	Select select = new Select(sort);
			select.selectByVisibleText("Order #");	
	  }
	  
	  @Test (priority=2)
	  public void NARROWBY() throws Exception {
		  	
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  	  highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[3]/div[1]")).click();
	  	  //Random NARROW BY Selections
	  	  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMob_filterOptions")));
		  WebElement WM1 = dr.findElement(By.className("skMob_filterOptions"));
		  List<WebElement> LWM1 = WM1.findElements(By.className("skMobff_filter "));
		  Random rand1 = new Random(System.currentTimeMillis());
		  WebElement W1 = LWM1.get(rand1.nextInt(LWM1.size()));
		  String Menu1 = W1.getText();
		  System.out.println("Menu Name : "+Menu1);
		  W1.click();
		  		  
		  //Random Order Selection After Filter
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_OrderMainList")));
		  WebElement WO1 = dr.findElement(By.className("skMobff_OrderMainList"));
		  List<WebElement> WP1 = WO1.findElements(By.className("skMobff_orders"));
		  int sizeMO = WP1.size();
		  
		  if(sizeMO!=0)
		  {
				  Random rand = new Random(System.currentTimeMillis());
				  WebElement WK1 = WP1.get(rand.nextInt(WP1.size()));
				  WK1.click();
				  
				  // Items Count Validation
				  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_pendingOrderItems")));
				  Q1 = dr.findElement(By.className("skMobff_pendingOrderItems"));
				  LQ1 = Q1.findElements(By.className("skMobff_productDetails"));
				  int LQ = LQ1.size();
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
				  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
				  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
		  } 
		  else
		  {
			  System.out.println("After Narrow By filter applied NO ITEMS DISPLAYED");
		  }
	  }
	  
	  @Test (priority=3)
	  public void VIEWALLCount() throws Exception {
		  
		  highlightElement(By.className("skMobff_viewAllItem")).click();
		  String Mcount = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[3]/div[1]/div[2]")).getText();
		  System.out.println("View All list Count : "+Mcount);
		  WebElement WE1 = dr.findElement(By.className("skMobff_pendingOrderItems"));
		  List<WebElement> LWE1 = WE1.findElements(By.className("skMobff_productDetails"));
		  int str = LWE1.size();
		  String Mcount1 = Integer.toString(str);
		  System.out.println("View All Items Count "+Mcount1);
		  if(Mcount.contains(Mcount1))
		  {
			  System.out.println("Count Matched with the Displayed Items Count "+Mcount);
		  }
		  else
		  {
			  System.out.println("Displayed Count is "+Mcount+" Number of Items displayed is "+Mcount1);
		  }
		  highlightElement(By.className("skMobff_backBtnIcon")).click();
	  }
	  
	  @Test (priority = 4)
	  public void Scan() throws Exception {
		  highlightElement(By.className("skMobff_headerIconContainer")).click();
		  highlightElement(By.id("id_menuListItem_1")).click();
		  Thread.sleep(3000);
		  highlightElement(orderAttr).click();
		  Thread.sleep(3000);
		  WebElement M1 = highlightElement(By.className("skMobff_pendingOrderItems"));
		  List<WebElement> LM1 = M1.findElements(By.className("skMobff_productDetails"));
		  int Ksize = LM1.size();
		  String K1 = "id_skMobff_productDetails_";
		  
		  for(int i=0; i<Ksize;i++)
		  {
			  highlightElement(By.id(K1+i)).click();
			  String SM1 = highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[1]/div[3]/div/div[2]/div[2]/div[2]")).getText();
			  System.out.println("SKU : "+SM1);
			  highlightElement(By.className("skMobff_backBtnIcon")).click();
			  highlightElement(By.className("skMobff_backBtnIcon")).click();
			  Thread.sleep(3000);
			  highlightElement(By.className("skMobff_orderIdTxt")).sendKeys(SM1);
			  highlightElement(By.className("skMobff_scanOkBtn")).click();
			  highlightElement(By.xpath("/html/body/div[6]/div[2]/div")).click();
			  highlightElement(orderAttr).click();
			  System.out.println("-----------------------------------------------");			  
		  }
		  highlightElement(By.className("skMobff_backBtnIcon")).click();
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
		  System.out.println("--------------------------------------------------------------");
	  }
}