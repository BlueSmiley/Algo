import org.junit.Test;

public class CompetitionTests {

	
	
	//choice of data structures:
	//arrays as they are very simple to work with
	//for floyd it is a very natural data structure to represent the matrice with 2d array
	//for djikstra arrays are worse than min-pq but its much easier to use so I used arrays.
	
	//theoretical difference in performance:
	//djikstra made for point to points not for all pairs like floyd.
	//floyd better for dense graphs as it is exhaustive through all pairs whereas djikstra is not so
	//better for sparse
	//floyd should have greater memory requirements, optimal djikstra only usess as much space as
	// vertices in queue to be processed 
	//floyd faster for this problem as need to find fastest between all pairs which floyd is better at
	
	
	
	
	//hmm imo since i implemented djikstra using arrays(still implements the algorithm) the differences
	//would be less noticeable.
	//djikstra isnt made for shortes path between all points but from one point.
	//it is probably better for sparse graphs compared to floyd
	//since floyd is exhaustive
	//the benefit of djikstra comes from less memory usage as you only need to store an extra 
	//min pq with entries to be updated rather than an entire adjacency table
	//As well as it is a continous algorithm that allows one to discover the shortest path
	//between one point to any other point in the graph starting from that point
    @Test
    public void testDijkstraConstructor() {
    	CompetitionDijkstra graph;
    	
    	graph = new CompetitionDijkstra("./input-A.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionDijkstra("./input-B.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-C.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionDijkstra("./input-D.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-E.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-F.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-G.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-H.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-I.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-J.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-K.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-L.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionDijkstra("./input-M.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionDijkstra("./input-N.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionDijkstra("./input.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionDijkstra("./input-N.txt",0,0,-1);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    }

    @Test
    public void testFWConstructor() {
    	System.out.println();
    	CompetitionFloydWarshall graph;
    	 graph = new CompetitionFloydWarshall("./input-A.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionFloydWarshall("./input-B.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-C.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionFloydWarshall("./input-D.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-E.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-F.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-G.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-H.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-I.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-J.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-K.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-L.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	//graph = new CompetitionFloydWarshall("./input-M.txt",50,60,70);
    	//System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionFloydWarshall("./input-N.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionFloydWarshall("./input-N.txt",0,0,-1);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	graph = new CompetitionFloydWarshall("./input.txt",50,60,70);
    	System.out.println(graph.timeRequiredforCompetition());
    	
    	
    }

    //TODO - more tests
    
}
