/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author
 *
 * @version 01/12/15 02:03:28
 */
public class FacebookCircles {

	private class Users
	{
		//usefull to have an internal class to represent user because we can change 
		//implementation of data types easily and only internally
		//not necessarily more memory efficent but better end result

		public int id;
		public int circleSize;
		Users(int userNumber)
		{
			//intial id = user number
			id = userNumber;
			//obviously no friends yet except self :(
			circleSize = 1;
		}
			
	}
	private Users[] users;
	//trades memory for speed by storing reference
	private int numberOfCircles;
  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int numberOfFacebookUsers) 
  {
	  /*my reasoning to use array:
	  * One of the simplest data structures
	  * Commonly required in this assignment to have quick access to arbitary members
	  * Static number of users(big one)
	  * Members always identified by index and not value hence no need to search
	  * Due to implementations only equal to or not comparisons(no order)
	  * 
	  */
	  users = new Users[numberOfFacebookUsers];	
	  for(int i = 0;i<users.length;i++)
		  users[i] = new Users(i);
	  numberOfCircles = numberOfFacebookUsers;
  }
  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int user1, int user2 ) 
  {
	  //makes cleaner code mainly
	  int nextUser;
	  while(users[user1].id != user1)
	  {
		  nextUser = users[user1].id;
		  //sorts it a bit while searching for subsequent searches
		  users[user1].id = users[nextUser].id;
		  user1 = nextUser;
	  }
	  
	  while(users[user2].id != user2)
	  {
		  nextUser = users[user2].id;
		  //sorts it a bit while searching for subsequent searches
		  users[user2].id = users[nextUser].id;
		  user2 = nextUser;
	  }
		  
	  if(users[user1] == users[user2])//always finds root which should be same object
		  return;
	  else
	  {
		  users[user1].circleSize = users[user1].circleSize + users[user2].circleSize;	
		  users[user2].id = users[user1].id;
		  //one less circle obviously as two circles got combined into one
		  numberOfCircles--;
	  }
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
    // TODO
    return numberOfCircles;
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
    // TODO
	int largestCircle = 1;
    for(int i=0;i<users.length;i++)
    	if(users[i].id == i && users[i].circleSize>largestCircle)
    		largestCircle = users[i].circleSize;
    return largestCircle;
  }

  /**
   * @return the size of the median circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
    // TODO
	int circleSum = 0;
	int uniqueCircles = 0;
	for(int i=0;i<users.length;i++)
		if(users[i].id == i)
		{
			circleSum += users[i].circleSize;
			uniqueCircles++;
		}
    return circleSum/uniqueCircles;
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
    // TODO
	  int smallestCircle = 0;
	    for(int i=0;i<users.length;i++)
	    	if(users[i].id == i && 
	    	(smallestCircle==0 || users[i].circleSize<smallestCircle))
	    		smallestCircle = users[i].circleSize;
	    return smallestCircle;
  }


}
