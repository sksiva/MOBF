package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class MyOrder extends SamplePO{
  
	@BeforeTest
	  public void beforeTest() {
		
	  }
	
	@Test
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
	
	@AfterTest
	  public void afterTest() {
		
	  }

}
