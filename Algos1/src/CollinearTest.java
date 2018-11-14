import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };
        int[] b1 = {5};			 int[] b2 = {10};		 int[] b3 = {15};
        int[] c1 = {15};			 int[] c2 = {10};		 int[] c3 = {5};
        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     
        		expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", 
        		expectedResult, Collinear.countCollinearFast(a1, a2, a3));
        
        assertEquals("countCollinear(" + Arrays.toString(b1) + "," + Arrays.toString(b2) + "," + Arrays.toString(b3) + ")",     
        		expectedResult, Collinear.countCollinear(b1, b2, b3));
        assertEquals("countCollinearFast(" + Arrays.toString(b1) + "," + Arrays.toString(b2) + "," + Arrays.toString(b3) + ")", 
        		expectedResult, Collinear.countCollinearFast(b1, b2, b3));
        
        assertEquals("countCollinear(" + Arrays.toString(c1) + "," + Arrays.toString(c2) + "," + Arrays.toString(c3) + ")",     
        		expectedResult, Collinear.countCollinear(c1, c2, c3));
        assertEquals("countCollinearFast(" + Arrays.toString(c1) + "," + Arrays.toString(c2) + "," + Arrays.toString(c3) + ")", 
        		expectedResult, Collinear.countCollinearFast(c1, c2, c3));
        
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    @Test
    public void testSortCorrect()
    {
    	int[] a1 = {11, 200, 41, 9, 10, 6};
    	int[] sa1 = {6,9,10,11,41,200};
    	Collinear.sort(a1);
    	assertTrue("sort value check " + Arrays.toString(a1),Arrays.equals(a1, sa1));
    	
    	

    }
    
    @Test
    public void testBinarySearchTrue()
    {
    	int[] a1 = {0,5,10,15};		int[] a2 = {0,3,5,10,15};
    	
    	assertTrue("Test true:Collinear.binarySearch(" + Arrays.toString(a1) + ",15)",Collinear.binarySearch(a1, 15));
    	assertTrue("Test true:Collinear.binarySearch(" + Arrays.toString(a2) + ",15)",Collinear.binarySearch(a2, 15));
    	
    	assertTrue("Test true:Collinear.binarySearch(" + Arrays.toString(a1) + ",5)",Collinear.binarySearch(a1, 5));
    	assertTrue("Test true:Collinear.binarySearch(" + Arrays.toString(a2) + ",5)",Collinear.binarySearch(a2, 5));
    	
    	assertTrue("Test true:Collinear.binarySearch(" + Arrays.toString(a1) + ",5)",Collinear.binarySearch(a1, 0));
    	assertTrue("Test true:Collinear.binarySearch(" + Arrays.toString(a2) + ",5)",Collinear.binarySearch(a2, 0));
    }
    
    @Test
    public void testBinarySearchFalse()
    {
    	int[] a1 = {0,5,10,15};		int[] a2 = {0,3,5,10,15};
    	assertFalse("Test false:Collinear.binarySearch(" + Arrays.toString(a1) + ",7)",
    			Collinear.binarySearch(a1, 7));
    	assertFalse("Test false:Collinear.binarySearch(" + Arrays.toString(a2) + ",7)",
    			Collinear.binarySearch(a2, 7));
    	
    }
    
    @Test
    public void testSingleBinaryTrue()
    {
    	int[] a1 = {15};
    	assertTrue("Test for single array true:Collinear.binarySearch(" + Arrays.toString(a1) + ",15)",
    			Collinear.binarySearch(a1, 15));
    }
    
    @Test
    public void testSingleBinaryFalse()
    {
    	int[] a1 = {15};
    	assertFalse("Test for single array false:Collinear.binarySearch(" + Arrays.toString(a1) + ",15)",
    			Collinear.binarySearch(a1, 7));
    }
    
    @Test
    public void testSortEfficent()
    {
    	int[] a = {15,5,10,20};
    	for(int i=1;i<a.length;i++)
    	{
    		int k = i-1;
    		for(;k>=0 && k<i && a[i]<a[k];k--);
    		k++;
    		int replacement = a[i];
    		while(k<=i)
    		{
    			int next = a[k];
    			a[k] = replacement;
    			k++;
    			replacement = next;
    		}
    		switch(i)
    		{
    		case 1:
    			assertTrue("sort value check 1" + Arrays.toString(a),
    					Arrays.equals(a, new int[]{5,15,10,20}));
    			break;
    		case 2:
    			assertTrue("sort value check 2" + Arrays.toString(a),
    					Arrays.equals(a, new int[]{5,10,15,20}));
    			break;
    		case 3:
    			assertTrue("sort value check 3" + Arrays.toString(a),
    					Arrays.equals(a, new int[]{5,10,15,20}));
    			break;
    		default:
    			System.err.print("Error in testSortEfficent" );
    			break;
    		}
    	}
    }
    
    @Test
    public void testZeroArray()
    {
    	int[] a1 = new int[0]; int[] a2 = {10}; int[] a3 = {15};
    	int[] b1 = {10};	int[] b2 = new int[0]; int[] b3 = {15};
    	int[] c1 = {15};	int[] c2 = {10};	int[] c3 = new int[0];
    	
    	int result = 0;
    	assertEquals(Arrays.toString(a1) + Arrays.toString(a2) + Arrays.toString(a3)
    	,result,Collinear.countCollinear(a1, a2, a3));
    	assertEquals(Arrays.toString(a1) + Arrays.toString(a2) + Arrays.toString(a3)
    	,result,Collinear.countCollinearFast(a1, a2, a3));
    	
    	assertEquals(Arrays.toString(b1) + Arrays.toString(b2) + Arrays.toString(b3)
    	,result,Collinear.countCollinear(b1, b2, b3));
    	assertEquals(Arrays.toString(b1) + Arrays.toString(b2) + Arrays.toString(b3)
    	,result,Collinear.countCollinearFast(b1, b2, b3));
    	
    	assertEquals(Arrays.toString(c1) + Arrays.toString(c2) + Arrays.toString(c3)
    	,result,Collinear.countCollinear(c1, c2, c3));
    	assertEquals(Arrays.toString(c1) + Arrays.toString(c2) + Arrays.toString(c3)
    	,result,Collinear.countCollinearFast(c1, c2, c3));
    	
    }
    
    @Test
    public void testNullArray()
    {
    	int[] a1 = null; int[] a2 = {10}; int[] a3 = {15};
    	int[] b1 = {10};	int[] b2 = null; int[] b3 = {15};
    	int[] c1 = {15};	int[] c2 = {10};	int[] c3 = null;
    	
    	int result = 0;
    	assertEquals("null" + Arrays.toString(a2) + Arrays.toString(a3)
    	,result,Collinear.countCollinear(a1, a2, a3));
    	assertEquals("null" + Arrays.toString(a2) + Arrays.toString(a3)
    	,result,Collinear.countCollinearFast(a1, a2, a3));
    	
    	assertEquals(Arrays.toString(b1) + "null" + Arrays.toString(b3)
    	,result,Collinear.countCollinear(b1, b2, b3));
    	assertEquals(Arrays.toString(b1) + "null" + Arrays.toString(b3)
    	,result,Collinear.countCollinearFast(b1, b2, b3));
    	
    	assertEquals(Arrays.toString(c1) + Arrays.toString(c2) + "null"
    	,result,Collinear.countCollinear(c1, c2, c3));
    	assertEquals(Arrays.toString(c1) + Arrays.toString(c2) + "null"
    	,result,Collinear.countCollinearFast(c1, c2, c3));
    	
    }
    
    @Test
    public void testBinarySearchError()
    {
    	int[] a1 = null;
    	int[] b1 = new int[0];
    	
    	assertFalse("Null",Collinear.binarySearch(a1,0));
    	assertFalse("Empty",Collinear.binarySearch(b1,0));
    }
    
    
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  You should read the lecture notes and/or book to understand how to correctly implement the main methods.
     *  You can use any of the provided classes to read from files, and time your code.
     *
     */
     public static void main(String[] args)
     {
	    	 /*String currentDirectory;
	    	 File file = new File(".");
	    	 currentDirectory = file.getAbsolutePath();
	    	 System.out.println("Current working directory : "+currentDirectory);
	    	 
    		 In scanner1 = new In("r01000-1.txt");
    		 In scanner2 = new In("r01000-2.txt");
    		 In scanner3 = new In("r01000-3.txt");
    		 int[] a1 = scanner1.readAllInts();
    		 int[] a2 = scanner2.readAllInts();
    		 int[] a3 = scanner3.readAllInts();
    		 Stopwatch watch = new Stopwatch();
    		 Collinear.countCollinearFast(a1, a2, a3);
    		 StdOut.println(watch.elapsedTime());
    		 watch = null;
    		 scanner1.close();
    		 scanner2.close();
    		 scanner3.close();
    		 
    		 In scanner4 = new In("r02000-1.txt");
    		 In scanner5 = new In("r02000-2.txt");
    		 In scanner6 = new In("r02000-3.txt");
    		 int[] a4 = scanner4.readAllInts();
    		 int[] a5 = scanner5.readAllInts();
    		 int[] a6 = scanner6.readAllInts();
    		 Stopwatch watch2 = new Stopwatch();
    		 Collinear.countCollinearFast(a4, a5, a6);
    		 StdOut.println(watch2.elapsedTime());
    		 watch = null;
    		 scanner4.close();
    		 scanner5.close();
    		 scanner6.close();
    		 
    		 In scanner7 = new In("r04000-1.txt");
    		 In scanner8 = new In("r04000-2.txt");
    		 In scanner9 = new In("r04000-3.txt");
    		 int[] a7 = scanner7.readAllInts();
    		 int[] a8 = scanner8.readAllInts();
    		 int[] a9 = scanner9.readAllInts();
    		 Stopwatch watch3 = new Stopwatch();
    		 Collinear.countCollinearFast(a7, a8, a9);
    		 StdOut.println(watch3.elapsedTime());
    		 watch = null;
    		 scanner7.close();
    		 scanner8.close();
    		 scanner9.close();
    		 
    		 In scanner10 = new In("r05000-1.txt");
    		 In scanner11 = new In("r05000-2.txt");
    		 In scanner12 = new In("r05000-3.txt");
    		 int[] a10 = scanner10.readAllInts();
    		 int[] a11 = scanner11.readAllInts();
    		 int[] a12 = scanner12.readAllInts();
    		 Stopwatch watch4 = new Stopwatch();
    		 Collinear.countCollinearFast(a10, a11, a12);
    		 StdOut.println(watch4.elapsedTime());
    		 watch = null;
    		 scanner10.close();
    		 scanner11.close();
    		 scanner12.close();*/
     }

}


