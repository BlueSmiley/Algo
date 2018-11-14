// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2018
 */

/**
 * Giving credit to all sources where I based my algorithms from just in case:
 * Algorithms and Data Structures lecture notes for all sorts except for mergeSort and Quicksort
 * Wikipedia for the merge sort algorithm for bottom up implementation.
 * Apparently based of Goldstine and von Neumanns algorithm
 * For quick sort optimisations of picking median and recursing to smaller sublist partition first
 * Robert SedgeWick
 * For algorithm logic of a concise method to quicksort into three partitions from
 * https://www.sigmainfy.com/blog/quick-sort-recap-3-way-partition-handling-duplicate-keys.html
 * 
 * If basing my code on any of these algorithms is considered plagiarism 
 * please notify me as soon as possible so I can rewrite the implementation
 * Afaik since i didnt copy code from any source and most of these algorithms
 * are public domain it should be fine but included this just in case
 * 
 * Also asked lecturer and from what I understood it was fine to do this but maybe I misunderstood
 */
 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
	 
    static double [] insertionSort (double a[]){
    	
        //starting from start of array
    	//for each new e;lement processed put in
    	//correct position in sublist up to the index
    	//of element currently being processed
    	int i,j;
    	double index;
    	
    	for(i=1;i<a.length;i++)
    	{
    		index = a[i];
    		j=i;
    		while((j>0) && (a[j-1]>index))
    		{
    			swap(a,j,j-1);
    			j=j-1;
    		}
    		a[j] = index;
    	}
    	return a;
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
	
		 //pick starting list bounds 
    	int lo = 0,hi = a.length-1;
    	qSort(a,lo,hi);
    	return a;
    }//end quicksort
    
    //recursive function that sorts a sublist
    //this version works efficently for duplicate values
    static private void qSort(double a[],int lo,int hi)
    {
    	if(lo>=hi)
    		return;
    	int i = lo,j = hi;
    	double pivot = pickPivot(a,lo,hi);
    	for(int n = lo;n<=j;)
    	{
    		if(a[n]<pivot)
    		{
    			swap(a,i,n);
    			i++;
    			if(n<= i)
    				n++; //prevent infinite loop where n<= i
    		}
    		else if(a[n] > pivot)
    		{
    			swap(a,j,n);
    			j--;
    		}
    		else
    			n++;
    	}
    	//memory efficency apparently to 
    	//recurse to smaller sublist first
    	if(i-lo < hi-j)
    	{
    		qSort(a,lo,i-1);
    		qSort(a,j+1,hi);
    	}
    	else
    	{
    		qSort(a,j+1,hi);
    		qSort(a,lo,i-1);
    	}
    	
    }
    
    //picks the median of the sublist as the pivot
    static private double pickPivot(double a[],int lo,int hi)
    {
    	double low = a[lo],medium,high;
    	if(a[hi] < low)
    	{
    		medium = low;
    		low = a[hi];
    	}
    	else
    		medium = a[hi];
    	if(a[lo + (hi-lo)/2] < low)
    	{
    		high = medium;
    		medium = low;
    		low = a[lo + (hi-lo)/2];
    	}
    	else if(a[lo + (hi-lo)/2] < medium)
    	{
    		high = medium;
    		medium = a[lo + (hi-lo)/2];
    	}
    	else
    		high = a[lo + (hi-lo)/2];
    	return medium;
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] mergeSort (double a[]){

		 //implementation as if this is a list
    	//because frequently used to sort lists
    	//not an in place sort
    	//the sorted sublist are moved to b
    	//used iterative over recursive implementation 
    	//as easier to visualise on lists
    	//assume each element is sorted in a sublist of size 1
    	//then double width of sublist each time 
    	//and sort new sublist until sublist = list size
    	
    	double b[] = new double[a.length];
    	for(int width = 1;width<a.length;width = 2*width)
    	{
    		for(int i=0;i<a.length;i = i + 2*width)
    		{
    			int ptrLeft = i;
    			int ptrRight = i + width<a.length?
    					i+width:a.length;
    			int ptrEnd = i + 2*width<a.length?
    					i+2*width:a.length;
    			int k = ptrLeft,j=ptrRight;
    			
    			for(int bindex = ptrLeft;bindex < ptrEnd;bindex++)
    			{
    				if(k<ptrRight && 
    						(j>= ptrEnd || a[k]<=a[j]))
    				//if there are elements in left and 
    				//(there are no elements in right or 
    				//the current left element is smaller
    				//than or equal current right element)
    				{
    					b[bindex] = a[k];
    					k++;
    				}
    				else
    				{
    					b[bindex] = a[j];
    					j++;
    				}
    			}
    		}
    		double[] c = a;
    		a = b;
    		b = c;
    	}
    	return a;
    }//end mergesort

    /**
     * Sorts an array of doubles using Shell Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] shellSort (double a[]){

		 //As shown in lecture h-sort list from a starting h
    	// all the way to one
    	 int h = 1;
    	 while(h<a.length/3)
    		 h = 3*h + 1; 
    	 // goes through sequences 1/2 (3^k - 1) I think
    	 
    	 while(h >= 1)
    	 {
    		 for(int i=h;i< a.length; i++)
    		 {
    			 for(int j=i;j>= h && (a[j] < a[j-h]); j-= h)
    				 swap(a,j,j-h);
    		 }
    		 
    		 h = h/3;
    	 }
		 return a;
    }//end shellsort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){

         //for each index starting from 0 swap with
    	//smallest remianing element in array
    	//do n times
    	for(int i=0; i<a.length-1;i++)
    	{
    		int min = i;
    		for(int j=i+1;j<a.length;j++)
    		{
    			if(a[j] < a[min])
    				min = j;
    		}
    		
    		swap(a,i,min);
    	}
    	return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Bubble Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] bubbleSort (double a[]){

         //moves the greatest value each time
    	//to the very end of the aray
    	//repeat procedure n times to sort n
    	//elements
    	//If no swaps in a pass then the array is fully sorted
    	boolean swaps = true;;
    	for(int i=0;i<a.length && swaps;i++)
    	{
    		swaps = false;
    		for(int j=1;j<(a.length-i);j++)
    		{
    			
    			if(a[j-1] > a[j])
    			{
    				swap(a,j-1,j);
    				swaps = true;
    			}
    		}
    	}
    	return a;
		 
    }//end bubblesort
    
    //swaps index i and j in array a
    private static double[] swap(double[] a,int i,int j)
    {
    	double temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    	return a;
    }

 }//end class

