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
	}

	void findStronglyConnectedComponents(){
		List<List<Integer>> answer = new ArrayList<>();
		for(int i=0; i<numberOfPoints; i++)
			answer.add(new ArrayList<>());
		boolean[] isSCC = new boolean[numberOfPoints];

		for(int vertex = 1; vertex < numberOfPoints; vertex++){
			if(!isSCC[vertex]){
				List<Integer> newSCC = new ArrayList<>();
				newSCC.add(vertex);
				for(int newVertex = vertex + 1; newVertex < numberOfPoints; newVertex++){
					if(!isSCC[newVertex] && pathExists(vertex, newVertex) && pathExists(newVertex, vertex)){
						isSCC[newVertex] = true;
						newSCC.add(newVertex);
					}
				}
				answer.add(newSCC);
			}
		}
		printSCC(answer);
	}

	private boolean pathExists(int source, int destination){
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

	private void printSCC(List<List<Integer>> answer){
		System.out.println("Strongly Connected Components List:\n");
		for(List<Integer> cycle : answer){
			for(int element : cycle){
				System.out.print(element + " ");
			}
			System.out.println("");
		}
	}
}

public class SCC{
	public static void main(String [] args){
		Graph graph = new Graph(5);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 1);
		graph.addEdge(3, 2);
		graph.addEdge(4, 5);
		graph.findStronglyConnectedComponents();
	}
}