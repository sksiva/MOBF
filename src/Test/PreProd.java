package Test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PreProd {
  public WebDriver dr;
  
  @BeforeTest
  public void beforeTest() {
	  //Mobile Emulator in Chrome
	  ChromeOptions op=new ChromeOptions();
      op.addArguments("--disable-popup-blocking");
      op.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
      //Reporter.log("User agent set as iphone!",bolConsole);
      System.setProperty("webdriver.chrome.driver","D:/SK Backups/Selenium/chromedriver.exe");
      dr = new ChromeDriver(op);
  }

  @Test (priority = '2')
  public void ScrollOrder() throws InterruptedException {
	  
	 // dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
	  Thread.sleep(4000);
	  for (int second = 0;; second++) {
		    if(second >=10){
		        break;
		    }
		    ((JavascriptExecutor) dr).executeScript("window.scrollBy(0,1500)", "");
		    Thread.sleep(3000);
		}
	  
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  System.out.println("Scroll Completed "+"&"+" Number of Orders are : "+WB1.size());
  }
  
  @Test (priority = '3')
  public void ViewOrder() throws InterruptedException {
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  System.out.println("***** Rand Val ***** ");
	  System.out.println(W2.getText());
	  W2.click();
	 
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
	//Go to Item Details page
	  WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.id("id_skMobff_productDetails_0")));
	  dr.findElement(By.id("id_skMobff_productDetails_0")).click();
	// Back to PO Items
	  WebElement ele2 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	// Back to PO
	  WebElement ele3 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
	  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();	  
  }
  
  @Test (priority = '4')
  public void UrgentOrder() {
	  
	  if(dr.findElements(By.xpath("//*[@id='id_skMobff_ordersList_0']/div[4]")).size() != 0){
		  System.out.println("Selected Order is an Urgent Order");
		  }
	  else{
		  System.out.println("Not An Urgent Order");
		  }
  }
  
  @Test (priority = '5')
  public void ORDERvalidation() throws InterruptedException {
	  //dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/stg/mobileFulfillment");
	  dr.findElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  Thread.sleep(3000);
	  dr.findElement(By.name("pendingOrder")).click();
	  Thread.sleep(3000);
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));	
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  String Str1 = W2.findElement(By.className("skMobff_OrderId")).getText();
	  //String Str1 = W2.getText();
	  
	  System.out.println("***** Str1 ***** ");
	  System.out.println(Str1);
	  W2.click();
	  
	  Thread.sleep(3000);
	  String Str2 = dr.findElement(By.id("skMobff_orderId")).getText();
	  System.out.println("***** Str2 ***** ");
	  System.out.println(Str2);
	  if(Str2.contains(Str1))
	  {
		  System.out.println("Your Selected Order is Opened : "+Str1);
	  }
	  else
	  {
		  System.out.println("Selected Order in PO screen is: "+Str1+" Opened Order is : "+Str2);
	  }
	  
	// Back to PO
	  WebDriverWait wait2 = new WebDriverWait(dr, 30);
		  WebElement ele3 = wait2.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
	    }
  
  @Test (priority = '6')
  public void QTYvalidation() throws InterruptedException{
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));	
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  String Str1 = W2.findElement(By.className("skMobff_ItemCount")).getText();
	  	  
	  System.out.println("***** Qty1 ***** ");
	  System.out.println(Str1);
	  W2.click();	  
	  Thread.sleep(3000);
	  WebElement WM1 = dr.findElement(By.className("skMobff_orderItems"));
	  List<WebElement> LL1 = dr.findElements(By.className("skMobff_productDetails"));
	  int i = LL1.size();
	  String Str2 = String.valueOf(i);
	  
	  //String Str2 = dr.findElement(By.id("skMobff_orderQty")).getText();
	  System.out.println("***** Qty2 ***** ");
	  System.out.println(Str2);
	  if(Str1.contains(Str2))
	  {
		  System.out.println("Order Items Qty are Same in PLP & PO Items page: "+Str1);
	  }
	  else
	  {
		  System.out.println("Qty in PLP is : "+Str1+" Qty in PO Items page : "+Str2+" item(s)");
	  }
	  
	// Back to PO
		  WebDriverWait wait2 = new WebDriverWait(dr, 30);
			  WebElement ele3 = wait2.until(ExpectedConditions.
						presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
			  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();
  }
  
  @Test (priority = '7')
  public void PRICEvalidation() throws InterruptedException{
	  
	  dr.findElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
	  Thread.sleep(3000);
	  dr.findElement(By.name("pendingOrder")).click();
	  Thread.sleep(3000);
	  WebElement W1 = dr.findElement(By.className("skMobff_OrderMainList"));	
	  List<WebElement> WB1 = W1.findElements(By.className("skMobff_orders"));
	  //Random Selection to PDP
	  Random rand = new Random(System.currentTimeMillis());
	  WebElement W2 = WB1.get(rand.nextInt(WB1.size()));
	  String Str1 = W2.findElement(By.className("skMobff_TotalPrice")).getText();
	  //String Str1 = W2.getText();
	  
	  System.out.println("***** Str1 ***** ");
	  System.out.println(Str1);
	  W2.click();
	  
	  Thread.sleep(3000);
	  String Str2 = dr.findElement(By.id("skMobff_orderValue")).getText();
	  System.out.println("***** Str2 ***** ");
	  System.out.println(Str2);
	  if(Str2.contains(Str1))
	  {
		  System.out.println("Your Selected Order's Total Price : "+Str1);
	  }
	  else
	  {
		  System.out.println("Order's Total Price in PLP page is: "+Str1+" Order's Total Price in Pending Order Item page is : "+Str2);
	  }
	  
	// Back to PO
	  WebDriverWait wait2 = new WebDriverWait(dr, 30);
		  WebElement ele3 = wait2.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath(".//*[@class='skMobff_backBtnIcon']")));
		  dr.findElement(By.xpath(".//*[@class='skMobff_backBtnIcon']")).click();	  
  }
  
  @Test (priority = '1')
  public void Swipe() throws InterruptedException{
	  dr.get("http://skavapoc:skava123@mobilestage.skavaone.com/skavastream/studio/reader/mobilefulfillment");
	  //Thread.sleep(20000);
	  WebDriverWait wait1 = new WebDriverWait(dr, 30);
		//Go to Item Details page
		  WebElement ele1 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div")));
		  
	  WebElement draggable = dr.findElement(By.xpath("//*[@id='id_skMobff_ordersList_1']/div[3]/div/div"));
      System.out.println("Location"+draggable.getLocation());
      System.out.println("Window size:"+ dr.manage().window().getSize().getWidth());
      int dragg=dr.manage().window().getSize().getWidth();
      System.out.println("dragg : "+ dragg);
      int positive=dragg/2+1;
      new Actions(dr).dragAndDropBy(draggable, positive,0).build().perform();
	    
  }
  
  @Test (enabled = true)
  public void TestOrder4(){
	  
  }
  
  @Test (enabled = true)
  public void TestOrder5(){
	  
  }
  
  @Test (enabled = true)
  public void TestOrder6(){
	  
  }
  
  @AfterTest
  public void afterTest() {
	  System.out.println("Scenario Completed Successfully");
	  //dr.quit();
  }
}