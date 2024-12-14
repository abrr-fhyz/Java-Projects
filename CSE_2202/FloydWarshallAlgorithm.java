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
		Edge otherEdge = new Edge(y, x, z);
		listOfEdges.add(edge);
		listOfEdges.add(otherEdge);
	}

	int[][] getInitialDistances(){
		int[][] distances = new int[numberOfPoints][numberOfPoints];
		for (int i = 0; i < numberOfPoints; i++) {
    		for (int j = 0; j < numberOfPoints; j++) {
    			if(i == j){
    				distances[i][j] = 0;
    				continue;
    			}
        		distances[i][j] = Integer.MAX_VALUE; 
    		}
		}
		for(Edge e : listOfEdges){
			distances[e.source][e.destination] = e.weight;
			distances[e.destination][e.source] = e.weight;
		}
		return distances;
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

class FloydWarshall{
	private int numberOfPoints;
	private Graph graph;

	FloydWarshall(int n){
		this.numberOfPoints = n;
		graph = new Graph(numberOfPoints);
	}

	void addEdge(int x, int y, int z){
        graph.addEdge(x, y, z);
    }

	void findShortestPath() {
    	int[][] distances = new int[numberOfPoints][numberOfPoints];
    	distances = graph.getInitialDistances();
    	
    	for(int k=0; k<numberOfPoints; k++){
    		for(int i=0; i<numberOfPoints; i++){
    			for(int j=0; j<numberOfPoints; j++){
    				if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE){
    					distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
    				}
    			}
    		}
    	}
    	listPaths(distances);
	}


	private void listPaths(int[][] distances){
		System.out.println("Floyd-Warshall for the given graph:\n");
		for(int vert=0; vert<numberOfPoints; vert++){
			System.out.println("For source: " + vert);
			int v = 0;
			for(int idx : distances[vert])
				System.out.println("\tVertex " + v++ + "-> distance: " + idx);

		}
	}
}

public class FloydWarshallAlgorithm{
	public static void main(String [] args){
		FloydWarshall shortPath = new FloydWarshall(9);
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
		shortPath.findShortestPath();
	}
}