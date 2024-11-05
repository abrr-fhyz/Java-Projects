import java.util.*;

class Graph {
	private List<List<Integer>> vertices;
	private int numberOfPoints = 0;

	Graph(int n){
		this.numberOfPoints = n+1;
		vertices = new ArrayList<>(numberOfPoints);
		for(int i=0; i<numberOfPoints; i++)
			vertices.add(new ArrayList<>());
	}

	void addEdge(int x, int y){
		vertices.get(x).add(y);
		vertices.get(y).add(x);
	}

	boolean pathExists(int source, int destination){
		boolean[] visited = new boolean[numberOfPoints];
		return dfs(source, destination, visited);
	}

	private boolean dfs(int current, int destination, boolean[] visited){
		if(current == destination)
			return true;
		visited[current] = true;
		for(int point : vertices.get(current)){
			if(!visited[point]){
				if(dfs(point, destination, visited))
					return true;
			}
		}
		return false;
	}
}

class KruskalObject{
	private List<Edge> edges;
	private int noOfVertices;
	private List<Edge> minimumSpanTree;

	KruskalObject(int v){
		this.noOfVertices = v;
		edges = new ArrayList<>();
	}

	void addEdge(int u, int v, int weight){
		Edge edge = new Edge(u, v, weight);
		edges.add(edge);
	}

	void findMST(){
		minimumSpanTree = new ArrayList<>();
		Graph graph = new Graph(noOfVertices);
		Collections.sort(edges, Comparator.comparingInt(edge -> edge.weight));
		for(Edge edge : edges){
			if(!graph.pathExists(edge.source, edge.destination)){
				minimumSpanTree.add(edge);
				graph.addEdge(edge.source, edge.destination);

				if(minimumSpanTree.size() == noOfVertices - 1)
					break;
			}
		}
	}

	void printMST(){
		int totalWeight = 0;
		System.out.println("Minimum Spanning Tree:\n");
		for(Edge edge : minimumSpanTree){
			System.out.println(edge.toString());
			totalWeight += edge.weight;
		}
		System.out.println("\nFinal Minimum Weight = " + totalWeight);
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

public class KruskalAlgorithm{
	public static void main(String [] args){
		KruskalObject mst = new KruskalObject(9);
		mst.addEdge(7, 6, 1);
		mst.addEdge(8, 2, 2);
		mst.addEdge(6, 5, 2);
		mst.addEdge(0, 1, 4);
		mst.addEdge(2, 5, 4);
		mst.addEdge(8, 6, 6);
		mst.addEdge(2, 3, 7);
		mst.addEdge(7, 8, 7);
		mst.addEdge(7, 8, 7);
		mst.addEdge(0, 7, 8);
		mst.addEdge(1, 2, 8);
		mst.addEdge(3, 4, 9);
		mst.addEdge(5, 4, 10);
		mst.addEdge(1, 7, 11);
		mst.addEdge(3, 5, 14);
		mst.findMST();
		mst.printMST();
	}
}