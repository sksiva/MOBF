package pak;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Del extends Attr{
	
	  @BeforeClass
	  public void Del1() throws Exception {
		System.out.println("Before Delete");
	  }
	  @Test
	  public void f1() {
		  System.out.println("Test");
		  
	  }
	  
	  @AfterClass
	  public void EOMO() throws Exception {
		System.out.println("After Delete"); 
		//dr.quit();
	  }
}