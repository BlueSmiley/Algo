import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2018
 */

/*Algorithms that order had impact on:
 * insertion sort: as we can see that when reverse ordered it had to traverse through entire array and for every element do i swaps where i is index
 * of next element.Due to performance improvement it basically only had to do n comparisons when sorted and 0 swaps
 * 
 * shell sort: Performance boost when sorted for similar reason as insertion sort.
 * Bubble: Due to optimisation it works best when already sorted only need n comparisons and nearly sorted.
 * 
 *  Biggest difference:
 *  bubble sort: biggest difference because bubble sort is trash and was really bad for reverse sorted because it always has to do 
 *  swaps for every element except last and bubble them all to end. But if sorted it doesnt have to swap anything so very efficent.
 *  
 *  Best/Worst scalability: merge and quick sort sweem to be tied for best while bubble sort takes an easy lead for worst
 *  
 *  Fastest: Hard to tell unfortunately due to the fact that the differences arent pronounced due to the efficency of the jvm and this computer
 * 
 */

/*
 * Java broke this haha,if the results dont make sense blame oracle not me
			10 random		100 random		1000 random		1000 few unique		1000 nearly ordered		1000 reverse order		1000 sorted
Insert		3.333			3.666			9					8.333				6.333						10.666				3
Quick		3.333			3.666			5					4					5							5.333				5.666
Merge		4				3.333			4					3.666				4							4.333				4
Shell		4				4				4.333				5					6							4					3.666
Selection	3.666			4.333			8.666				8.333				7.666						9					8
Bubble		3.666			4				11.333				11.333				9.333						12.666				3.333
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	double a[] = new double[0];
    	double sortedA[] = new double[0];
    	
    	SortComparison.insertionSort(a);
    	assertTrue(Arrays.equals(a, sortedA));
    	
    	SortComparison.bubbleSort(a);
    	assertTrue(Arrays.equals(a, sortedA));
    	
    	SortComparison.selectionSort(a);
    	assertTrue(Arrays.equals(a, sortedA));
    	
    	SortComparison.shellSort(a);
    	assertTrue(Arrays.equals(a, sortedA));
    	
    	SortComparison.quickSort(a);
    	assertTrue(Arrays.equals(a, sortedA));
    	
    	SortComparison.mergeSort(a);
    	assertTrue(Arrays.equals(a, sortedA));
    }

    @Test
    public void test10()
    {
    	double a[] = {0,5,14,26.0,1.5,43,9,5,26,7};
    	double sortedA[] = {0,1.5,5,5,7,9,14,26,26,43};
    	SortComparison.insertionSort(a);
    	assertTrue(Arrays.toString(sortedA).equals(Arrays.toString(a)));
    	
    	a = new double[]{0,5,14,26.0,1.5,43,9,5,26,7};
    	SortComparison.bubbleSort(a);
    	assertTrue(Arrays.toString(sortedA).equals(Arrays.toString(a)));
    	
    	a = new double[]{0,5,14,26.0,1.5,43,9,5,26,7};
    	SortComparison.selectionSort(a);
    	assertTrue(Arrays.toString(sortedA).equals(Arrays.toString(a)));
    	
    	a = new double[]{0,5,14,26.0,1.5,43,9,5,26,7};
    	SortComparison.shellSort(a);
    	assertTrue(Arrays.toString(sortedA).equals(Arrays.toString(a)));
    	
    	a = new double[]{0,5,14,26.0,1.5,43,9,5,26,7};
    	SortComparison.quickSort(a);
    	assertTrue(Arrays.toString(sortedA).equals(Arrays.toString(a)));
    	
    	a = new double[]{0,5,14,26.0,1.5,43,9,5,26,7};
    	SortComparison.mergeSort(a);
    	assertTrue(Arrays.toString(a),Arrays.toString(sortedA).equals(Arrays.toString(a)));
    	
    	a = new double[]{5,0,14,26.0,1.5,43,9,5,26,7};
    	SortComparison.insertionSort(a);
    	assertTrue(Arrays.toString(a),Arrays.toString(sortedA).equals(Arrays.toString(a)));
    	
    	a = new double[]{5,0,14,26.0,1.5,43,9,5,26,7};
    	SortComparison.quickSort(a);
    	assertTrue(Arrays.toString(a),Arrays.toString(sortedA).equals(Arrays.toString(a)));
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
    	Scanner sc = null;
    	try
    	{
    		//Yes I know this is the longer pointless way
    		System.out.println(System.getProperty("user.dir"));
    		String filePath = "./src/numbers10.txt";
    		Pattern pattern = Pattern.compile("\\s*(\\d+)\\s*");
    		Matcher matcher = pattern.matcher(filePath);
    		matcher.find();
    		int nol = Integer.parseInt(matcher.group(1));
    		File f = new File(filePath);
    		sc = new Scanner(f);
    		double[] a = new double[nol];
    		for(int i=0;i<nol;i++)
    		{
    			a[i] = Double.parseDouble(sc.nextLine().trim());
    		}
    		int average = 3;
    		long total = 0;
    		for(int i=0;i<average;i++)
    		{
    			double b[] = a.clone();
    			long currentTime = System.currentTimeMillis();
    			SortComparison.insertionSort(b);
    			System.out.println(System.currentTimeMillis() - currentTime);
    			total += System.currentTimeMillis() - currentTime;
    		}
    		System.out.println(total/((double)average));
    		//fun fact java compiler breaks this doesnt work just redid this 3 times
    	}
    	catch(Exception e){e.printStackTrace();}
    	finally
    	{
    		if(sc!=null)
    			sc.close();
    	}
    }

}

