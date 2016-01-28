package Test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MOBFMyOrder extends Attributes{
	
	  @BeforeClass
	  public void Del1() throws Exception {
		System.out.println("************************* My Orders *************************");
	  }
	  
	  @Test (priority=2)
	  public void NARROWBY() throws Exception {
		  	
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  	  highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[3]/div[1]")).click();
	  	  
	  	  //Random NARROW BY Selections
		  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("skMob_filterOptions")));
		  WebElement WM1 = dr.findElement(By.className("skMob_filterOptions"));
		  List<WebElement> LWM1 = WM1.findElements(By.className("skMobff_filter"));
		  Random rand1 = new Random(System.currentTimeMillis());
		  WebElement W1 = LWM1.get(rand1.nextInt(LWM1.size()));
		  String M1 = W1.getText();
		  System.out.println("M2 : "+M1);
		  W1.click();
		  		  
		  //Random Order Selection After Filter
		  WebElement ele2 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.className("skMobff_OrderMainList")));
		  WebElement WO1 = dr.findElement(By.className("skMobff_OrderMainList"));
		  List<WebElement> WP1 = WO1.findElements(By.className("skMobff_orders"));
		  int MOsize = WP1.size();
		  System.out.println("My Orders Count : "+MOsize);
		  
		  if(MOsize!=0){
				  Random rand = new Random(System.currentTimeMillis());
				  WebElement WK1 = WP1.get(rand.nextInt(WP1.size()));
				  WK1.click();
				  
				  //Ranomly Check an Item's Category
				  WebElement ele3 = wait1.until(ExpectedConditions.
							presenceOfElementLocated(By.className("skMobff_orderItems")));
				  WebElement X1 = dr.findElement(By.className("skMobff_orderItems"));
				  List<WebElement> LX1 = X1.findElements(By.className("skMobff_productDetails "));
				  Random rand2 = new Random(System.currentTimeMillis());
				  WebElement WX1 = LX1.get(rand1.nextInt(LX1.size()));
				  String M2 = WX1.findElement(By.className("skMobff_Value")).getText();
				  System.out.println("Narrow By Value in My Order page : "+M2);
				  
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
		  }
		  else
		  {
			  System.out.println("No Items found after Filter in My Orders Page");
		  }
		  
		  // Back to PO
		  WebDriverWait wait2 = new WebDriverWait(dr, 30);
		  WebElement ele4 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();		  
	  }
	  
	  @Test (priority=1)
	  public void SORTBY() throws Exception {
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 40);
		  	WebElement ele1 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.className("skMobff_sortLabel")));
		  	highlightElement(By.className("skMobff_sortLabel")).click();
		  	WebElement sort = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[2]/select"));
		  
			Select select = new Select(sort);
			select.selectByVisibleText("Order #");
			System.out.println("--------------------------------------------------------------");	
	  }
	  
	  @Test (enabled=false)
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
	  
	  @Test (priority=4)
	  public void VIEWALL() throws Exception {
		  
		  highlightElement(By.className("skMobff_viewAllItem")).click();
		  Thread.sleep(3000);
		  String Mcount = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[3]/div[1]/div[2]")).getText();
		  System.out.println("MCount 1 : "+Mcount);
		  WebElement WE1 = dr.findElement(By.className("skMobff_pendingOrderItems"));
		  List<WebElement> LWE1 = WE1.findElements(By.className("skMobff_productDetails"));
		  int str = LWE1.size();
		  //int i = 1234;
		  String Mcount1 = Integer.toString(str);
		  System.out.println("MCount 2 : "+Mcount1);
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
	  
	  @Test (priority = 5)
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
	  
	  @Test (priority = 6)
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
	  
	  @Test (priority = 7)
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
	  
	  @Test (priority = 8)
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
	  
	  @AfterClass
		  public void EOMO() throws Exception {
		  System.out.println("************************* End of My Orders *************************");
		}
}