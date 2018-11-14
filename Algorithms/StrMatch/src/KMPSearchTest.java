import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

public class KMPSearchTest {

  @Test
  public void testEmpty(){
    assertEquals("Empty text or pattern is invalid",-1,KMPSearch.searchFirst("",""));
    assertEquals("Empty text or pattern is invalid",0,KMPSearch.searchAll("",""));
    assertFalse("Empty text or pattern is invalid",KMPSearch.contains("",""));
  }
  
  @Test
  public void testAll(){
	  	String pattern = "ab";
	    String text = "aba";
	    assertEquals(text + " in " + pattern,0,KMPSearch.searchFirst(text,pattern));
	    assertEquals(text + " in " + pattern,1,KMPSearch.searchAll(text,pattern));
	    assertTrue(text + " in " + pattern,KMPSearch.contains(text,pattern));
	    
	    pattern = "ab";
	    text = "abab";
	    assertEquals(text + " in " + pattern,0,KMPSearch.searchFirst(text,pattern));
	    assertEquals(text + " in " + pattern,2,KMPSearch.searchAll(text,pattern));
	    assertTrue(text + " in " + pattern,KMPSearch.contains(text,pattern));
	    
	    pattern = "ca";
	    text = "abab";
	    assertEquals(text + " in " + pattern,-1,KMPSearch.searchFirst(text,pattern));
	    assertEquals(text + " in " + pattern,0,KMPSearch.searchAll(text,pattern));
	    assertFalse(text + " in " + pattern,KMPSearch.contains(text,pattern));
  
  }
  
  public static void main(String[] args)
  {
	  String filePath = "./src/BUSES_SERVICE_0(1).json";
	  File f = new File(filePath);
	  Scanner sc = null;
	  String file = null;
	  try
	  {
		  sc = new Scanner(f);
		  file = sc.useDelimiter("\\Z").next();//hacky method but saves having to use stringbuilder
		  System.out.println(KMPSearch.searchAll(file,"VehicleNo"));
		  System.out.println(KMPSearch.contains(file,"16555"));
		  System.out.println(KMPSearch.searchFirst(file,"HAMPTON PARK"));
		  System.out.println(KMPSearch.contains(file,"9043409"));
	  }
	  catch(Exception e){e.printStackTrace();}
  }
}
