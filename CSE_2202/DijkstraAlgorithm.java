import java.util.*;

class Graph {
	private List<List<Edge>> listOfEdges;
	private int numberOfPoints = 0;

	Graph(int n){
		this.numberOfPoints = n+1;
		listOfEdges = new ArrayList<>(numberOfPoints);
		for(int i=0; i<numberOfPoints; i++)
			listOfEdges.add(new ArrayList<>());
	}

	void addEdge(int x, int y, int z){
		Edge edge = new Edge(x, y, z);
		Edge otherEdge = new Edge(y, x, z);
		listOfEdges.get(x).add(edge);
		listOfEdges.get(y).add(otherEdge);
	}

	List<Edge> getNeighbours(int x){
		return listOfEdges.get(x);
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

class Dijkstra{
	private int numberOfPoints;
	private Graph graph;

	Dijkstra(int n){
		this.numberOfPoints = n;
		graph = new Graph(numberOfPoints);
	}

	void addEdge(int x, int y, int z){
        graph.addEdge(x, y, z);
    }

	void findShortestPath(int source) {
    	int[] distances = new int[numberOfPoints];
    	Arrays.fill(distances, -1);
    	distances[source] = 0;

    	PriorityQueue<Edge> toVisit = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
    	toVisit.add(new Edge(source, source, 0));

    	while (!toVisit.isEmpty()) {
        	Edge current = toVisit.poll();
        	int vertex = current.destination;

        	List<Edge> neighbours = graph.getNeighbours(vertex);
        	for (Edge edge : neighbours) {
           		int neighbour = edge.destination;
            	int newDistance = distances[vertex] + edge.weight;

            	if (newDistance < distances[neighbour] || distances[neighbour] == -1) {
                	distances[neighbour] = newDistance;
                	toVisit.add(new Edge(vertex, neighbour, newDistance));
            	}
        	}
    	}

    	listPaths(distances, source);
	}


	private void listPaths(int[] distances, int src){
		System.out.println("Dijkstra from " + src + ":");
		int vert = 0;
		for(int idx : distances)
			System.out.println("Vertex " + vert++ + "-> distance: " + idx);
	}
}

public class DijkstraAlgorithm{
	public static void main(String [] args){
		Dijkstra shortPath = new Dijkstra(9);
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