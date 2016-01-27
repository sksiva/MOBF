package pak;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Del extends Attr{
	
	  @BeforeClass
	  public void Del1() throws Exception {
		System.out.println("Before Delete");
	  }
	  @Test
	  public void f1() throws Exception {
		  System.out.println("Test1");
		  highlightElement(By.xpath(".//*[@class='skMobff_headerMenuIcon']")).click();
		  System.out.println("Xpath"+orderAttr);
		  System.out.println("Order from PO"+Str2);
	  }
	  
	  @AfterClass
	  public void EOMO() throws Exception {
		System.out.println("After Delete"); 
		//dr.quit();
	  }
}