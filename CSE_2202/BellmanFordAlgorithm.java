import java.util.*;

class Graph {
	private List<Edge> listOfEdges;
	private int numberOfPoints = 0;

	Graph(int n){
		this.numberOfPoints = n;
		listOfEdges = new ArrayList<>(numberOfPoints);
	}

	void addEdge(int x, int y, int z){
		Edge edge = new Edge(x, y, z);
		listOfEdges.add(edge);
	}

	List<Edge> getEdgeList(){
		return this.listOfEdges;
	}

}

class Edge{
	int source;
	int destination;
	int weight;

	Edge(int a, int b, int c){
		this.source = a;
		this.destination = b;
		this.weight = c;
	}

	@Override
	public String toString(){
		String string = "" + source + " -> " + destination + " : " + weight;
		return string;
	} 
}

class BellmanFord{
	private int numberOfPoints;
	private Graph graph;

	BellmanFord(int n){
		this.numberOfPoints = n;
		graph = new Graph(numberOfPoints);
	}

	void addEdge(int x, int y, int z){
        graph.addEdge(x, y, z);
    }

	void findShortestPath(int source) {
    	int[] distances = new int[numberOfPoints];
    	Arrays.fill(distances, Integer.MAX_VALUE);
    	distances[source] = 0;
    	for(int i=0; i<numberOfPoints; i++){
    		for(Edge e : graph.getEdgeList()){
    			if(distances[e.source] != Integer.MAX_VALUE && distances[e.source] + e.weight < distances[e.destination]){
    				if(i == numberOfPoints-1){
    					System.out.println("This has a negative Cycle.");
    					return;
    				}
    				distances[e.destination] = distances[e.source] + e.weight;
    			}
    		}
    	}
    	
    	listPaths(distances, source);
	}


	private void listPaths(int[] distances, int src){
		System.out.println("Bellman Ford from " + src + ":");
		int vert = 0;
		for(int idx : distances)
			System.out.println("Vertex " + vert++ + "-> distance: " + idx);
	}
}

public class BellmanFordAlgorithm{
	public static void main(String [] args){
		BellmanFord shortPath = new BellmanFord(9);
		shortPath.addEdge(7, 6, 1);
		shortPath.addEdge(8, 2, 2);
		shortPath.addEdge(6, 5, 2);
		shortPath.addEdge(0, 1, 4);
		shortPath.addEdge(2, 5, 4);
		shortPath.addEdge(8, 6, 6);
		shortPath.addEdge(2, 3, 7);
		shortPath.addEdge(7, 8, 7);
		shortPath.addEdge(7, 8, 7);
		shortPath.addEdge(0, 7, 8);
		shortPath.addEdge(1, 2, 8);
		shortPath.addEdge(3, 4, 9);
		shortPath.addEdge(5, 4, 10);
		shortPath.addEdge(1, 7, 11);
		shortPath.addEdge(3, 5, 14);
		shortPath.findShortestPath(0);
	}
}