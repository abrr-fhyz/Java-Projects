import java.util.*;

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

class PrimObject{
	private List<Edge> minSpanTree;
	private List<Edge> listOfEdges;
	private int noOfVertices;
	private List<List<Integer>> graph;

	PrimObject(int v){
		this.noOfVertices = v;
		listOfEdges = new ArrayList<>();
		graph = new ArrayList<>(noOfVertices);
		for(int i=0; i<noOfVertices; i++)
			graph.add(new ArrayList<>());
	}

	void addEdge(int x, int y, int weight){
		Edge edge = new Edge(x, y, weight);
		listOfEdges.add(edge);
		graph.get(x).add(y);
		graph.get(y).add(x);
	}

	void findMST() {
		minSpanTree = new ArrayList<>();
		PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
		boolean[] visited = new boolean[noOfVertices];

		visited[0] = true;
		for(int neighbour : graph.get(0)){
			if(!visited[neighbour]){
				Edge retrievedEdge = getEdge(0, neighbour);
				if(retrievedEdge != null)
					queue.offer(retrievedEdge);
			}
		}

		while(!queue.isEmpty() && minSpanTree.size() < noOfVertices - 1){
			Edge edge = queue.poll();
			int nextVertex = !visited[edge.source]? edge.source : edge.destination;
			if(!visited[nextVertex]){
				minSpanTree.add(edge);
				visited[nextVertex] = true;
				for(int neighbour : graph.get(nextVertex)){
					if(!visited[neighbour]){
						Edge retrievedEdge = getEdge(nextVertex, neighbour);
						if(retrievedEdge != null)
							queue.offer(retrievedEdge);
					}
				}
			}
		}

	}

	void printMST(){
		int totalWeight = 0;
		System.out.println("Minimum Spanning Tree:\n");
		for(Edge edge : minSpanTree){
			System.out.println(edge.toString());
			totalWeight += edge.weight;
		}
		System.out.println("\nFinal Minimum Weight = " + totalWeight);
	}

	private Edge getEdge(int u, int v){
		for(Edge e : listOfEdges){
			if(e.source == u && e.destination == v)
				return e;
			if(e.destination == u && e.source == v)
				return e;
		}
		return null;
	}
}

public class PrimAlgorithm{
	public static void main(String [] args){
		PrimObject mst = new PrimObject(9);
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