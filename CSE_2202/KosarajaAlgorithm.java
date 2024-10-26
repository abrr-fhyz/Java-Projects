import java.util.*;

class Node{
	private int value;
	private boolean visited = false;
	private int discoveryTime = -1;
	private int finishTime = -1;
	private List<Node> neighbours;

	Node(int val){
		this.value = val;
		neighbours = new ArrayList<>();
	}

	int getValue(){
		return this.value;
	}

	void setVisited(){
		this.visited = true;
	}

	void reset(){
		this.visited = false;
	}

	boolean isVisited(){
		return visited;
	}

	void discoveryTime(int x){
		this.discoveryTime = x;
	}

	void finishTime(int x){
		this.finishTime = x;
	}

	void addNeighbour(Node x){
		this.neighbours.add(x);
	}

	List<Node> getNeighbours(){
		return this.neighbours;
	}

	@Override
	public String toString(){
		String result = this.getValue() + " : [ ";
		for(Node node : neighbours){
			result += node.getValue() + " ";
		}
		result += "]";
		return result;
	}
}


class Graph{
	private int numberOfPoint = 0;
    private List<Node> adj;
    private List<Node> transpose;
    private int time = 0;
    private Stack<Node> stack;

    Graph(int x){
    	this.numberOfPoint = x;
    	adj = new ArrayList<>(numberOfPoint);
    	transpose = new ArrayList<>(numberOfPoint);
    	stack = new Stack<>();
    }

    void addEdge(int x, int y){
    	addEdgeInList(x, y, adj);
    	addEdgeInList(y, x, transpose);
    }

    void algoKosaraju(){
    	System.out.println("Kosraja's Algorithm of Strongly Connected Components: ");
    	while(!stack.isEmpty()){
    		Node node = stack.pop();
    		Node transNode = getNode(node.getValue(), transpose);
    		if(transNode != null && !transNode.isVisited()){
    			System.out.print("SCC: ");
    			dfsTranspose(transNode);
    			System.out.println("");
    		}
    	}
    	setUnvisited();
    }

    void depthFirstSearch(int source){
    	stack.clear();
    	Node idx = getNode(source, adj);
    	if(idx == null)
    		return;
    	System.out.print("DFS Traversal: ");
    	dfsMethod(idx);
    	System.out.println("\n");
    	setUnvisited();
    }

    void printGraph() {
    	System.out.println("Graph: ");
        for (Node node : adj) {
            System.out.println(node);
        }
    }

    void printTranspose() {
    	System.out.println("Transpose of Graph: ");
    	for (Node node : transpose) {
            System.out.println(node);
        }
    }

    private void dfsTranspose(Node idx){
    	idx.setVisited();
    	System.out.print(idx.getValue() + " ");
    	List<Node> neighbours = idx.getNeighbours();
    	for(Node next : neighbours){
    		if(!next.isVisited())
    			dfsTranspose(next);
    	}

    }

    private void addEdgeInList(int x, int y, List<Node> relevantList){
    	Node node = getNode(x, relevantList);
    	if(node == null){
    		node = new Node(x);
    		relevantList.add(node);
    	}
    	Node newNode = getNode(y, relevantList);
    	if(newNode == null){
    		newNode = new Node(y);
    		relevantList.add(newNode);
    	}
    	node.addNeighbour(newNode);
    }

    private void dfsMethod(Node idx){
    	idx.discoveryTime(time);
    	time++;
    	System.out.print(idx.getValue() + " ");
    	idx.setVisited();
    	List<Node> neighbours = idx.getNeighbours();
    	for(Node nextNode : neighbours){
    		if(!nextNode.isVisited())
    			dfsMethod(nextNode);
    	}
    	time++;
    	idx.finishTime(time);
    	stack.push(idx);
    }

    private Node getNode(int num, List<Node> relevantList){
    	Node idx;
    	for(Node node : relevantList){
    		if(node.getValue() == num){
    			idx = node;
    			return idx;
    		}
    	}
    	return null;
    }

    private void setUnvisited(){
    	for(Node node : adj)
    		node.reset();
    }
}

public class KosarajaAlgorithm{
	public static void main(String [] args){
		Graph graph = new Graph(5);
		graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 5);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.printGraph();
        graph.printTranspose();
        graph.depthFirstSearch(1);
        graph.algoKosaraju();
	}
}