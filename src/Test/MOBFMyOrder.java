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
	
	public static WebElement Q1;
	public static List<WebElement> LQ1;
	
	  @BeforeClass
	  public void Del1() throws Exception {
		System.out.println("************************* My Orders *************************");
	  }
	  
	  @Test (enabled = false)
	  public void SORTBY() throws Exception {
		  	WebDriverWait wait1 = new WebDriverWait(dr, 40);
		  	Thread.sleep(4000);
		  	wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_sortLabel")));
		  	highlightElement(By.className("skMobff_sortLabel")).click();
		  	WebElement sort = highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[2]/select"));
		  	Select select = new Select(sort);
			select.selectByVisibleText("Order #");	
	  }
	  
	  @Test (enabled = false)
	  public void NARROWBY() throws Exception {
		  	
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  	  highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[3]/div[1]")).click();
	  	  
	  	  //Random NARROW BY Selections
	  	  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMob_filterOptions")));
		  WebElement WM1 = highlightElement(By.className("skMob_filterOptions"));
		  List<WebElement> LWM1 = WM1.findElements(By.className("skMobff_filter "));
		  Random rand1 = new Random(System.currentTimeMillis());
		  WebElement W1 = LWM1.get(rand1.nextInt(LWM1.size()));
		  String Menu1 = W1.getText();
		  System.out.println("Menu1 : "+Menu1);
		  W1.click();
		  		  
		  //Random Order Selection After Filter
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_OrderMainList")));
		  WebElement WO1 = highlightElement(By.className("skMobff_OrderMainList"));
		  List<WebElement> WP1 = WO1.findElements(By.className("skMobff_orders"));
		  int sizeMO = WP1.size();
		  
		  if(sizeMO!=0)
		  {
				  Random rand = new Random(System.currentTimeMillis());
				  WebElement WK1 = WP1.get(rand.nextInt(WP1.size()));
				  System.out.println("***** Rand Val ***** ");
				  System.out.println(WK1.getText());
				  WK1.click();
				  
				  // Items Count Validation
				  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_pendingOrderItems")));
				  Q1 = highlightElement(By.className("skMobff_pendingOrderItems"));
				  LQ1 = Q1.findElements(By.className("skMobff_productDetails"));
				  int LQ = LQ1.size();
				  System.out.println("Size : "+LQ);
				  int flag=0;
				  if(Menu1!="Clear All")
				  {
						  for(int i=0;i<LQ;i++)
						  {
							  String Z1 = "//*[@id='id_skMobff_productDetails_";
							  String Z2 = "']/div[1]/div/div[2]";
							  String NM = highlightElement(By.xpath(Z1+i+Z2)).getText();
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
	  
	  @Test (enabled = false)
	  public void VIEWALL() throws Exception {
		  
		  highlightElement(By.className("skMobff_viewAllItem")).click();
		  Thread.sleep(3000);
		  String Mcount = highlightElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[3]/div[1]/div[2]")).getText();
		  System.out.println("MCount 1 : "+Mcount);
		  WebElement WE1 = dr.findElement(By.className("skMobff_pendingOrderItems"));
		  List<WebElement> LWE1 = Q1.findElements(By.className("skMobff_productDetails"));
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
	  
	  @Test (enabled = false)
	  public void ORDERvalidation() throws Exception {
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
		  WB1 = highlightElement(orderAttr);
		  String S1 = WB1.findElement(By.className("skMobff_OrderId")).getText();
		  WB1.click();
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("skMobff_orderId")));
		  
		  String S2 = highlightElement(By.id("skMobff_orderId")).getText();
		  if(S2.contains(S1))
		  {
			  System.out.println("Selected Order Number in PLP is Same in PO Items page for the Order "+S1);
		  }
		  else
		  {
			  System.out.println("Selected Order in PLP is: "+S1+" But Opened Order Number is : "+S2);
		  }	  
		  
		  // Back to PO
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
		  System.out.println("Order Number is Validated for "+Str2+" Successfully");
		  System.out.println("--------------------------------------------------------------");
	  }
	  
	  @Test (enabled = false)
	  public void QTYvalidation() throws Exception {
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
		  WB1 = highlightElement(orderAttr);
		  String Q1 = WB1.findElement(By.className("skMobff_ItemCount")).getText();
		  WB1.click();
		  //Get Number of Items in the Order
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("skMobff_orderItems")));
		  WebElement WM1 = highlightElement(By.className("skMobff_orderItems"));
		  List<WebElement> LL1 = WM1.findElements(By.className("skMobff_productDetails"));
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
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
		  System.out.println("Order Qty is Validated for "+Str2+" Successfully");
		  System.out.println("--------------------------------------------------------------");
	  }
	  
	  @Test (enabled = false)
	  public void PRICEvalidation() throws Exception {
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
		  WB1 = highlightElement(orderAttr);
		  String P1 = WB1.findElement(By.className("skMobff_TotalPrice")).getText();
		  WB1.click();
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("skMobff_orderValue")));
		  String P2 = highlightElement(By.id("skMobff_orderValue")).getText();
		  if(P2.contains(P1))
		  {
			  System.out.println("Selected Order Price page in PLP is Same in PO Items page for the Order "+P1);
		  }
		  else
		  {
			  System.out.println("Selected Order in PLP is: "+P1+" But Opened Order Price is : "+P2);
		  }	  
		  
		  // Back to PO
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
		  System.out.println("Order Price is Validated for "+Str2+" Successfully");
		  System.out.println("--------------------------------------------------------------");
	  }
	  
	  @Test (enabled = false)
	  public void DATEvalidation() throws Exception {
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
		  WB1 = highlightElement(orderAttr);
		  String D1 = WB1.findElement(By.className("skMobff_OrderDate")).getText();
		  WB1.click();
		  
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("skMobff_orderDate")));
		  String D2 = highlightElement(By.id("skMobff_orderDate")).getText();
		  if(D1.contains(D2))
		  {
			  System.out.println("Selected Order Date in PLP is Same in PO Items page for the Order "+D1);
		  }
		  else
		  {
			  System.out.println("Selected Order in PLP is: "+D1+" But Opened Order's Date is : "+D2);
		  }	 
		  	  
		  // Back to PO
		  wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  highlightElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
		  System.out.println("Order Date is Validated for "+Str2+" Successfully");
		  System.out.println("--------------------------------------------------------------");
	  }
	  @Test (priority = 1)
	  public void Scan() throws Exception {
		  highlightElement(By.className("skMobff_headerIconContainer")).click();
		  highlightElement(By.id("id_menuListItem_1")).click();
		  Thread.sleep(3000);
		  highlightElement(orderAttr).click();
		  
		  Thread.sleep(3000);
		  System.out.println("Scan 1");
		  WebElement M1 = highlightElement(By.className("skMobff_pendingOrderItems"));
		  List<WebElement> LM1 = M1.findElements(By.className("skMobff_productDetails"));
		  int Ksize = LM1.size();
		  System.out.println("menu Sizeeeeeeeeee : "+Ksize);		  
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
		  
		  
		  
		  /*for(WebElement LM : LM1)
		  {
			  Thread.sleep(3000);
			  System.out.println("For Loop Item Name: "+LM.getText());
			  //highlightElement(By.className("skMobff_backBtnIcon")).click();
			  //highlightElement(orderAttr).click();
			  System.out.println("B4 LM Click");
			  Thread.sleep(4000);
			  LM.click();
			  System.out.println("After LM Click");
			  //String SM1 = highlightElement(By.className("skMobff_Value")).getText();
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
		  }*/
	  }
}