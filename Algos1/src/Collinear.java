// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * Experimental Performance:
     * -------------------------
     *  Write the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              .826
     *  2000              5.342
     *  4000              42.02
     *
     *  Assuming that the running time of your algorithm is of the form aN^b,
     *  estimate 'b' and 'a' by fitting a line to the experimental points:
     *
     *  b = 2.834
     *  a = 2.509E-09
     *
     *  What running time do you predict using your results for input size 5000?
     *  What is the actual running time you get with such an input?
     *  What is the error in percentage?
     *
     *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
     *
     *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
     *  ------------------------------------------------------------------------------------------------
     *  5000              76.274                               77.932                            2.174
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: O(N^3)
     *
     *  Explanation: Each array has N elements.the brute force method searches through every 
     *  combination of elements in each array for a collinear point.It will always only check every
     *  element the same number of time.This can thus be classified as a N^3 time algorithm as the
     *	largest term will always be N^3
     */
	
	public static final int Y1 = 1;
	public static final int Y2 = 2;
	public static final int Y3 = 3;
	
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
    	int count = 0;
    	if(a1!=null && a2!=null && a3!=null && a1.length>0 && a2.length>0 && a3.length>0)
    	{
    		for(int i=0;i<a1.length;i++)
    		{
    			for(int j=0;j<a2.length;j++)
    			{
    				for(int k=0;k<a3.length;k++)
    				{
    					if(a1[i]*(Y2-Y3) + a2[j]*(Y3-Y1) + a3[k]*(Y1-Y2)==0)
    						count++;
    				}
    			}
    		}
    	}
    	return count;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     * Experimental Performance:
     * -------------------------
     *  Measure the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              .204
     *  2000              .364
     *  4000              1.564
     *  5000              2.48
     *
     *
     *  Compare Implementations:
     *  ------------------------
     *  Show the sppedup achieved by this method, using the times you got from your experiments.
     *
     *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
     *  ---------------------------------------------------------------------------------
     *  1000              4
     *  2000              14
     *  4000              26
     *  5000              31
     *
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: O(N^2*logN)
     *
     *  Explanation: Two for loops iterate over two sets of N elements giving N^2 time.Binary search
     *  has log(n) time based on my calculations below.So for N^2 searches we perform another search 
     *  of log N time
     *
     *
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
    	sort(a3);
    	double inverseValue = 1.0/(double)(Y2-Y1);
    	int count = 0;
    	if(a1!=null && a2!=null && a3!=null && a1.length>0 && a2.length>0 && a3.length>0)
    	{
    		for(int i=0;i<a1.length;i++)
    		{
    			for(int j=0;j<a2.length;j++)
    			{
    				int requiredValue = (int)(((double)(a1[i]*(Y2-Y3) + a2[j]*(Y3-Y1)))*inverseValue);
    				if(binarySearch(a3, requiredValue))
    					count++;
    			}

    		}
    	}
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance:O(n^2) ~(N*(N-1))/2
     *
     *  Explanation: We need to examine atleast N elements to sort
     *  In the worst case where the sequence is sorted from highest to lowest,
     *  we need to examine k elements for each element
     *  in the worst case we can assume we examine all elements before the current element
     *  For half the elements we examine <n-1/2 elems and for the other half we examine >n-1/2
     *  As k is a linearly increasing amount we can say the average amount of elements is (n-1)/2
     *	Hence N*(n-1)/2
     *
     */
    static void sort(int[] a)
    {
    	if(a!=null && a.length>0)
    	{
    		for(int i=1;i<a.length;i++)
    		{
    			int k = i-1;
    			for(;k>=0 && a[i]<a[k];k--);
 
    			k++;
				int replacement = a[i];
    			while(k<=i)
    			{
    				int next = a[k];
    				a[k] = replacement;
    				k++;
    				replacement = next;
    			}
    			
    		}
    	}
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: O(lg(N)) ~lg(N)
     *
     *  Explanation: n/2^k = 1 where k is the number of searches in the worst case as 
     *  the search space is divided by two each time.
     *
     */
    static boolean binarySearch(int[] a, int x)
    {
    	if(a!=null && a.length>0)
    	{
    		int upperbound = a.length-1;
    		int lowerbound = 0;
    		while(upperbound>lowerbound)
    		{
    			int index = (upperbound + lowerbound)/2;
    			if(a[index]>x)
    			{
    				upperbound = index - 1;
    			}
    			else if(a[index]<x)
    			{
    				lowerbound = index + 1;
    			}
    			else
    			{
    				return true;
    			}
    		}
    		if(lowerbound==upperbound && a[lowerbound]==x)
    			return true;
    		return false;
    	}
    	else
    	{
    		return false;
    	}
    }

}
