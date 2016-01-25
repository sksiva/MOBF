package Test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyOrder extends SamplePO{
  
	@BeforeTest
	  public void StartMO() {
		System.out.println("Before in MyORder");
	  }
	
	@Test
	  public void T1() throws Exception {
		  
		System.out.println("Test in MyORder");
	  	/*WebDriverWait wait1 = new WebDriverWait(dr, 30);
	  	WebElement ele1 = wait1.until(ExpectedConditions.
				presenceOfElementLocated(By.className("skMobff_sortLabel")));
	  	highlightElement(By.className("skMobff_sortLabel")).click();
	  	WebElement sort = dr.findElement(By.xpath("//*[@id='skPageLayoutCell_1_id-2']/div/div/div/div[2]/div[1]/select"));
		Select select = new Select(sort);
		select.selectByVisibleText(" Order # ");
		System.out.println("--------------------------------------------------------------");*/		
  }
	
	@AfterTest
	  public void EndofMO() {
		System.out.println("After in MyORder");
		
	  }

}
