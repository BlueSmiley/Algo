import java.io.File;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	
	double distTo[][];
	int edgeTo[][];
	int edgeCount;
	int sA,sB,sC;
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC)
    {
    	//line 1 = number of vertices
    	//line 2 = number of directed paths
    	//>=line3 = two vertices for the edge and a double for distance
    	this.sA = sA;
    	this.sB = sB;
    	this.sC = sC;
    	try
    	{
    		File f = new File(filename);
    		Scanner sc = null;
    		String file = null;
    		sc = new Scanner(f);
    		int i = 0;
    		while(sc.hasNextLine())
    		{
    			String[] line = sc.nextLine().trim().split("\\s+");
    			if(i==0)
    			{

    				distTo = new double[Integer.parseInt(line[0])][Integer.parseInt(line[0])];
    				edgeTo = new int[Integer.parseInt(line[0])][Integer.parseInt(line[0])];

    				for(int j=0;j<distTo.length;j++)
    				{
    					for(int k=0;k<distTo[j].length;k++)
    					{
    						distTo[j][k] = Integer.MAX_VALUE;
    						if(j == k)
    							distTo[j][k] = 0;
    					}
    				}
    			}
    			else if(i==1)
    			{
    				edgeCount = Integer.parseInt(line[0]);
    			}
    			else
    			{
    				distTo[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Double.parseDouble(line[2]);
    				edgeTo[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Integer.parseInt(line[0]);
    				if(Double.parseDouble(line[2])<0)
    				{
    					distTo = new double[0][0];
    					edgeTo = new int[0][0];
    					return;
    				}
    			}
    			i++;
    		}

    		for(int j=0;j<distTo.length;j++)
    		{
    			shortestPath(j);
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		distTo = new double[0][0];
			edgeTo = new int[0][0];
			return;
    	}
    }

    //ok slight cheat here when modern versions of djikstra algorithm use min priority queue and
    //only relax the least priority element it avoids you haveing to re-relax an already relaxed vertex
    //to save time of iterating through the array and not having a min-pq i have re-relax any updated entries
    private void shortestPath(int vertice)
    {
    	boolean[] marked =  new boolean[distTo.length];
    	marked[vertice] = true;
    	while(true)
    	{
	    	int unvisited = -1;	//
	    	for(int i=0;i<distTo.length;i++)
	    	{
	    		if(marked[i]==false && distTo[vertice][i] != Integer.MAX_VALUE)
	    		{
	    			unvisited = i;
	    			break;	//when a unvisited vertice has been found break out
	    		}
	    	}
	    	if(unvisited == -1)
	    		return;	//no more vertices available to be relaxed
	    	marked[unvisited] = true;	//mark vertice as visited
	    	for(int i=0;i<distTo.length;i++)
	    	{
	    		//relax all edges from newly marked vertice
	    		if(distTo[vertice][unvisited]+distTo[unvisited][i] < distTo[vertice][i])
	    		{
	    			distTo[vertice][i] = distTo[vertice][unvisited]+distTo[unvisited][i];
	    			marked[i] = false;
	    			edgeTo[vertice][i] = unvisited;
	    		}
	    	}
    	}
    	
    }

    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition()
    {
    	double longest = 0;
    	int slowest = Math.min(sC,Math.min(sA, sB));
    	//iterate through aray to find longest distance
    	for(int i=0;i<distTo.length;i++)
    	{
    		for(int j=0;j<distTo[i].length;j++)
    		{
    			//System.out.print(edgeTo[i][j] + "	");
    				if(distTo[i][j]==Integer.MAX_VALUE)
    					return -1;
    				else if(distTo[i][j]>longest)
    					longest = distTo[i][j];
    		}
    		//System.out.print("\n");
    	}
    	//System.out.println(Math.ceil((longest*1000)));
    	
    	if(longest == 0 || slowest <=0)
    		return -1;
        return (int)Math.ceil(((longest*1000)/slowest));
    }

}
