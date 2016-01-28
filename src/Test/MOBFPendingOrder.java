package Test;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class MOBFPendingOrder extends Attributes{
	
  @Test (priority = 1)
  public void GetOrder() throws InterruptedException {
	  
	  System.out.println("************************* Pending Orders *************************");
	  dr.get("https://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WebElement ele1 = wait1.until(ExpectedConditions.
	  					presenceOfElementLocated(By.className("skMobff_OrderMainList")));
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));	
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	 
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  
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
	  	WebDriverWait wait1 = new WebDriverWait(dr, 50);
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
  
  @Test (priority = 3)
  public void UrgentOrder() throws Exception {
	  
	  String Str4 = "/div[4]";		  
	  String UrgOrder = dr.findElement(By.xpath(Str1+Str2+Str3+"/div[2]/div[1]")).getText();
	  if(dr.findElements(By.xpath(Str1+Str2+Str3+Str4)).size() != 0){
		  System.out.println("Selected Order "+Str2+" is An Urgent Order");
		}
	  
	  else{
		  System.out.println("Selected Order "+Str2+" is Not An Urgent Order");
		  }
	  System.out.println("--------------------------------------------------------------");
  }
  
  @Test (priority = 4)
  public void ORDERvalidation() throws Exception {
	  
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WB1 = highlightElement(orderAttr);
	  String S1 = WB1.findElement(By.className("skMobff_OrderId")).getText();
	  WB1.click();
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.id("skMobff_orderId")));
	  
	  String S2 = dr.findElement(By.id("skMobff_orderId")).getText();
	  if(S2.contains(S1))
	  {
		  System.out.println("Selected Order Number in PLP is Same in PO Items page for the Order "+S1);
	  }
	  else
	  {
		  System.out.println("Selected Order in PLP is: "+S1+" But Opened Order Number is : "+S2);
	  }	  
	  
	  // Back to PO
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	  System.out.println("Order Number is Validated for "+Str2+" Successfully");
	  System.out.println("--------------------------------------------------------------");
  }
  
  @Test (priority = 5)
  public void QTYvalidation() throws Exception {
	  
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WB1 = highlightElement(orderAttr);
	  String Q1 = WB1.findElement(By.className("skMobff_ItemCount")).getText();
	  WB1.click();
	  //Get Number of Items in the Order
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("skMobff_orderItems")));
	  WebElement WM1 = dr.findElement(By.className("skMobff_orderItems"));
	  List<WebElement> LL1 = dr.findElements(By.className("skMobff_productDetails"));
	  int i = LL1.size();
	  String Q2 = String.valueOf(i);
	  if(Q1.contains(Q2))
	  {
		  System.out.println("Order Items Qty are Same in both PLP & PO Items page: "+Q1);
	  }
	  else
	  {
		  System.out.println("Qty in PLP is : "+Q1+". But Qty in PO Items page : "+Q2+" item(s)");
	  }
	  
	  // Back to PO
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	  System.out.println("Order Qty is Validated for "+Str2+" Successfully");
	  System.out.println("--------------------------------------------------------------");
  }
  
  @Test (priority = 6)
  public void PRICEvalidation() throws Exception {
	  
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WB1 = highlightElement(orderAttr);
	  String P1 = WB1.findElement(By.className("skMobff_TotalPrice")).getText();
	  WB1.click();
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.id("skMobff_orderValue")));
	  String P2 = dr.findElement(By.id("skMobff_orderValue")).getText();
	  if(P2.contains(P1))
	  {
		  System.out.println("Selected Order Price page in PLP is Same in PO Items page for the Order "+P1);
	  }
	  else
	  {
		  System.out.println("Selected Order in PLP is: "+P1+" But Opened Order Price is : "+P2);
	  }	  
	  
	  // Back to PO
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	  System.out.println("Order Price is Validated for "+Str2+" Successfully");
	  System.out.println("--------------------------------------------------------------");
  }
  
  @Test (priority = 7)
  public void DATEvalidation() throws Exception {
	  
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WB1 = highlightElement(orderAttr);
	  String D1 = WB1.findElement(By.className("skMobff_OrderDate")).getText();
	  WB1.click();
	  
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.id("skMobff_orderDate")));
	  String D2 = dr.findElement(By.id("skMobff_orderDate")).getText();
	  if(D1.contains(D2))
	  {
		  System.out.println("Selected Order Date in PLP is Same in PO Items page for the Order "+D1);
	  }
	  else
	  {
		  System.out.println("Selected Order in PLP is: "+D1+" But Opened Order's Date is : "+D2);
	  }	 
	  	  
	  // Back to PO
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	  System.out.println("Order Date is Validated for "+Str2+" Successfully");
	  System.out.println("--------------------------------------------------------------");
  }
  
  
  
  @Test (priority = 8)
  public void Navigation() throws Exception {
	  
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  //Click PO
	  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  highlightElement(By.name("pendingOrder")).click();
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_headerMenuIcon']")));
	  //Click My Orders
	  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  highlightElement(By.name("myOrder")).click();
	  WebElement ele2 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_headerMenuIcon']")));
	  
	  //Click Completed Orders
	  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  highlightElement(By.name("completedOrder")).click();
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_headerMenuIcon']")));
	  //Click Archive Orders
	  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  highlightElement(By.name("archiveOrder")).click();
	  WebElement ele4 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_headerMenuIcon']")));
	  //Click PO
	  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  highlightElement(By.name("pendingOrder")).click();	  
  }
  
  @Test (priority = 9)
  public void SORTBY() throws Exception {
	  
	  	WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  	WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("skMobff_sortLabel")));
	  	highlightElement(By.className("skMobff_sortLabel")).click();
	  	WebElement sort = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[1]/select"));
		Select select = new Select(sort);
		select.selectByVisibleText(" Order # ");
		System.out.println("--------------------------------------------------------------");		
  }
  
  @Test (priority = 10)
  public void NARROWBY() throws Exception {
	  
	      WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  	  highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[2]")).click();
	  	  
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
		  System.out.println("Narrow By Category Name : "+M2);
		  
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
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
  }
  
  @Test (priority = 11)
  public void SWIPE() throws Exception {
	  
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WebElement ele2 = wait1.until(ExpectedConditions.presenceOfElementLocated(orderAttr));
	  WebElement draggable = dr.findElement(orderAttr);
	  System.out.println("Swiped Order Numb : "+draggable.findElement(By.className("skMobff_OrderId")).getText());
	  
	  WebElement FW = dr.findElement(By.id("id_skMobff_ordersList_0"));
	  int FX = FW.getLocation().getX();
	  int FY = FW.getLocation().getY()+150;
	    
	  //X-Axis From
	  int FX1 = FW.getLocation().getX()+25;
	    
	  //X-Axis To
	  int FX2 = FW.getLocation().getX()+240;
		
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
      
      /*//Click My Orders
	  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  highlightElement(By.name("myOrder")).click();	 */
	  System.out.println("--------------------------------------------------------------");
  }
  
  @Test (priority = 12)
  public void OrderCount() throws Exception {
	  
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("skMobff_OrderMainList")));
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  // Get Order count from Pending Order page
	  int X1 = WB1.size();
	  String numOrders = Integer.toString(X1);
	  System.out.println("Number of Orders in PLP are : "+numOrders);
	  
	  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  //Get PO icon Count 
	  String POcount = dr.findElement(By.id("id_menuListItemCount_0")).getText();
	  if(numOrders.equals(POcount))
	  {
		  System.out.println("Order Count in PO screen is same as PO icon count : "+numOrders);
	  }
	  else
	  {
		  System.out.println("Order Count in PO screen is : "+numOrders+" PO Icon count is : "+POcount);
	  }
	  highlightElement(By.name("myOrder")).click();
	  
	  System.out.println("--------------------------------------------------------------");
  }

  @AfterClass
  public void EOPO() throws Exception {
	  System.out.println("************************* End of Pending Orders *************************");  	  
  }
}