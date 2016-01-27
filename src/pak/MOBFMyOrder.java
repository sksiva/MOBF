package pak;

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
	  @Test (priority=1)
	  public void NARROWBY() throws Exception {
		  System.out.println("Test1");
		
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
	  
	  @Test (priority=2)
	  public void SORTBY() throws Exception {
		  
		  WebDriverWait wait1 = new WebDriverWait(dr, 30);
		  	WebElement ele1 = wait1.until(ExpectedConditions.
					presenceOfElementLocated(By.className("skMobff_sortLabel")));
		  	highlightElement(By.className("skMobff_sortLabel")).click();
		  	WebElement sort = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[2]/select"));
		  
			Select select = new Select(sort);
			select.selectByVisibleText("Order #");
			System.out.println("--------------------------------------------------------------");	
	  }
	  
	  @Test (priority=3)
	  public void VIEWALL() throws Exception {
		  
		  highlightElement(By.className("skMobff_viewAllItem")).click();
	  }
	  
	  @AfterClass
		  public void EOMO() throws Exception {
		  System.out.println("After Delete");
		}
}