package pak;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Del extends Attr{
	
	  @AfterTest
	  public void Del1() throws Exception {
		System.out.println("Del1");  	  
	  }
	  @Test
	  public void f1() {
		  System.out.println("Test");
	  }
	  
	  @AfterTest
	  public void EOPO() throws Exception {
		System.out.println("EOPO");  	  
	  }
}