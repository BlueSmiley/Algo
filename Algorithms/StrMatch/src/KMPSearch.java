
public class KMPSearch {

  /*
   * Bus Service Questions:
   *
   * 1. How many total vehicles is there information on?
   *    //987
   *
   * 2. Does the file contain information about the vehicle number 16555?
   *    //true
   *
   * 3. Locate the first record about a bus heading to HAMPTON PARK
   *    //19774
   *
   * 4. Does the file contain information about the vehicle number 9043409?
   *    //False
   */

   /*
    * The method checks whether a pattern pat occurs at least once in String txt.
    *
    */
  private static final int RADIX = 256; //currently set to extended ascii char number
  public static boolean contains(String txt, String pat) {
    //Just get the first match by KMP and if no match then return false
	//using kmp only for this seems really inefficent I think but its whats asked here
	  //The reason why I think so is because if we are searching whether a string is in the text
	  //theres a decent chance it isnt in the text and its easier/more efficent to prove the absence
	  //of the pattern ie.the Moore method thingy than the presence of it.
	if(searchFirst(txt,pat)!=-1)
		return true;
    return false;
  }

  /*
   * The method returns the index of the first ocurrence of a pattern pat in String txt.
   * It should return -1 if the pat is not present
   */
  public static int searchFirst(String txt, String pat) {
    // simple dfa method:construct a dfa that actually precomputes the value of the dfa for all chars in alphabet
	  //Then run the dfa algorithm
	  //seems memory inefficent and computationally inefficent to calculate for chars not in pattern but much simpler to
	  //implement
	  //Also might be slightly more efficent in certain cases if rather than precomputing entire dfa we compute while matching
	int M = pat.length();
	int N = txt.length();
	if(M<=0 || N <= 0)
		return -1;
	int[][] dfa = new int[RADIX][M];
	dfa[pat.charAt(0)][0] = 1;
	int X = 0;
	for(int j=1;j<M;j++)
	{
		for(int c=0;c<RADIX;c++)
			dfa[c][j] = dfa[c][X]; //copy mismatch cases,good thing java initialises arrays all 0 in this case
		dfa[pat.charAt(j)][j] = j+1; //set match case to next state
		X = dfa[pat.charAt(j)][X]; //how much of a subsequence has been matched in the main sequence being matched
	}
	
	int i=0,j=0;
	for(;i<N && j<M;i++)
	{
		j = dfa[txt.charAt(i)][j];
	}
	if(j == M) 
		return i-M; //Give location of match
    return -1; //no match
  }

  /*
   * The method returns the number of non-overlapping occurences of a pattern pat in String txt.
   */
  public static int searchAll(String txt, String pat) 
  {
    //idea is very inefficent but essentially get substrings of the text every time match found till textsize<pattern length
	int M = pat.length();
    int N = txt.length();
    int count = 0;
    if(M<=0 || N <= 0)
		return count;
    while(N>=M)
    {
    	int find = searchFirst(txt,pat);
    	if(find == -1)
    		N = 0;	//no more matches parsed entire text
    	else
    	{
    		count++;
    		N = N-(find + M);
    		txt = txt.substring(find+M);
    	}
    }
    return count;
  }
}
