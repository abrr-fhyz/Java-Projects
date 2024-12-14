import java.util.*;

class Graph {
	private List<List<Edge>> listOfVertices;
	private List<Edge> listOfEdges;
	private int numberOfPoints = 0;
	private int superSource = 0;

	Graph(int n){
		this.numberOfPoints = n+1;
		this.superSource = n;
		listOfEdges = new ArrayList<>(numberOfPoints);
		listOfVertices = new ArrayList<>(numberOfPoints);
		for(int i=0; i<numberOfPoints; i++)
			listOfVertices.add(new ArrayList<>());
	}

	void addEdge(int x, int y, int z){
		Edge edge = new Edge(x, y, z);
		listOfEdges.add(edge);
	}

	void addSuperSource(){
		for (int i = 0; i < superSource; i++) {
            listOfEdges.add(new Edge(superSource, i, 0));
        }
	}

	List<Edge> getEdgeList(){
		return listOfEdges;
	}

	List<Edge> getNeighbours(int x){
		return listOfVertices.get(x);
	}

	int getSuperSource(){
		return superSource;
	}

	void buildGraph(int[] distances) {
        Iterator<Edge> iterator = listOfEdges.iterator();
        while (iterator.hasNext()) {
            Edge e = iterator.next();
            if (e.source == superSource) {
                iterator.remove();
                continue;
            }
            addRelaxedEdge(e.source, e.destination, e.weight + distances[e.source] - distances[e.destination]);
        }
    }

	private void addRelaxedEdge(int x, int y, int z){
		Edge edge = new Edge(x, y, z);
		Edge otherEdge = new Edge(y, x, z);
		listOfVertices.get(x).add(edge);
		listOfVertices.get(y).add(otherEdge);
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

class Johnson{
	private int numberOfPoints;
	private Graph graph;

	Johnson(int n){
		this.numberOfPoints = n;
		graph = new Graph(numberOfPoints);
	}

	void addEdge(int x, int y, int z){
        graph.addEdge(x, y, z);
    }

    void findShortestPath() {
    	graph.addSuperSource();
    	int[] relaxedDistances = runBellmanFord(graph.getSuperSource());
    	if(relaxedDistances == null){
    		System.out.println("Execution Terminated.");
    		return;
    	}
    	graph.buildGraph(relaxedDistances);
    	for(int i=0; i<numberOfPoints; i++){
    		runDjikstra(i);
    	}
    	
    }

	int[] runBellmanFord(int source) {
    	int[] distances = new int[numberOfPoints+1];
    	Arrays.fill(distances, Integer.MAX_VALUE);
    	distances[source] = 0;
    	for(int i=0; i<numberOfPoints+1; i++){
    		for(Edge e : graph.getEdgeList()){
    			if(distances[e.source] != Integer.MAX_VALUE && distances[e.source] + e.weight < distances[e.destination]){
    				if(i == numberOfPoints){
    					System.out.println("This has a negative Cycle.");
    					return null;
    				}
    				distances[e.destination] = distances[e.source] + e.weight;
    			}
    		}
    	}
    	
    	return distances;
	}

	void runDjikstra(int source) {
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
			System.out.println("\tVertex " + vert++ + "-> distance: " + idx);
	}
}

public class JohnsonAlgorithm{
	public static void main(String [] args){
		Johnson shortPath = new Johnson(9);
		shortPath.addEdge(7, 6, 1);
		shortPath.addEdge(8, 2, 2);
		shortPath.addEdge(6, 5, 2);
		shortPath.addEdge(0, 1, 4);
		shortPath.addEdge(2, 5, 4);
		shortPath.addEdge(8, 6, 6);
		shortPath.addEdge(2, 3, 7);
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