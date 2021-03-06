import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

public class TSTTest {

  @Test
  public void testEmpty(){
    TST<Integer> trie = new TST<>();
    trie.put("", 100);
    assertEquals("size of an empty trie should be 0",0, trie.size());
    assertFalse("searching an empty trie should return false",trie.contains(""));
    assertNull("getting from an empty trie should return null",trie.get(""));
    assertEquals("number of elements in a list of all elements should be 0",0,trie.keysWithPrefix("").size());
  }
  
  @Test
  public void testAll(){
	  TST<Integer> trie = new TST<>();
	  trie.put("trie", 0);
	  assertEquals("size = 1",1, trie.size());
	  assertNull("getting non-existent element",trie.get(""));
	  assertTrue("getting trie",trie.get("trie").equals(0));
	  assertFalse("searching for non-existent",trie.contains("tri"));
	  assertTrue("searching for trie",trie.contains("trie"));
	  assertEquals("should be 1",1,trie.keysWithPrefix("tr").size());
	  trie.put("trier", 0);
	  trie.put("tried", 0);
	  trie.put("trieee", 0);
	  trie.put("tritrie", 0);
	  trie.put("triard", 0);
	  trie.put("trieric", 0);
	  assertEquals("should be 5",5,trie.keysWithPrefix("trie").size());
  }
  
  public static void main(String[] args)
  {
	  JSONParser parser = new JSONParser();
	  TST<Long> tree = new TST<Long>();
	  try
	  {
		  JSONArray a = (JSONArray) parser.parse(new FileReader("./src/BUSES_SERVICE_0(1).json"));
		  for (Object o : a)
		  {
		    JSONObject vehicleInfo = (JSONObject) o;
		    String destination = (String) vehicleInfo.get("Destination");
		    Long value = tree.get(destination);
		    if(value == null)
		    	value = 1l;
		    else
		    	value += 1l;
		    tree.put(destination, value);
		    
		  }
		  
		  System.out.println(tree.size());
		  System.out.println(tree.get("SOUTHSIDE"));
		  System.out.println(tree.get(tree.keysWithPrefix("DOWN").get(0)));
		  
		  
		  String filePath = "./src/google-books-common-words(1).txt";
		  tree = new TST<Long>();
		  File f = new File(filePath);
		  Scanner sc = null;
		  String file = null;
		  try
		  {
			  sc = new Scanner(f);
			  while(sc.hasNextLine())
			  {
				  String[] line = sc.nextLine().split("\\s+");
				  tree.put(line[0], Long.parseLong(line[1]));
			  }
		  }
		  catch(Exception e){e.printStackTrace();}
		  
		  System.out.println(tree.size());
		  System.out.println(tree.get("ALGORITHM"));
		  System.out.println(tree.contains("EMOJI"));
		  System.out.println(tree.contains("BLAH"));
		  System.out.println(tree.keysWithPrefix("TEST").size());
	  }
	  catch(Exception e){e.printStackTrace();}
  }
}
