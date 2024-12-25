import java.util.*;

class Node {
    private char value;
    private boolean visited = false;
    private int discoveryTime = -1;
    private int low = -1;
    private List<Node> neighbours;
    private Node parent = null;

    Node(char val) {
        this.value = val;
        neighbours = new ArrayList<>();
    }

    char getValue() {
        return this.value;
    }

    void setVisited() {
        this.visited = true;
    }

    void reset() {
        this.visited = false;
    }

    boolean isVisited() {
        return visited;
    }

    void setDiscoveryTime(int x) {
        this.discoveryTime = x;
    }

    int getDiscoveryTime() {
        return this.discoveryTime;
    }

    void setLowValue(int x) {
        this.low = x;
    }

    int getLowValue() {
        return this.low;
    }

    void addNeighbour(Node x) {
        this.neighbours.add(x);
    }

    void setParent(Node x) {
        this.parent = x;
    }

    Node getParent() {
        return this.parent;
    }

    List<Node> getNeighbours() {
        return this.neighbours;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getValue() + " : [ ");
        for (Node node : neighbours) {
            result.append(node.getValue()).append(" ");
        }
        result.append("]");
        return result.toString();
    }
}

class Graph {
    private int numberOfNodes;
    private List<Node> adj;
    private int time = 0;
    private boolean[] isArticulation;

    Graph(int x) {
        this.numberOfNodes = x;
        adj = new ArrayList<>();
        isArticulation = new boolean[numberOfNodes+1];
    }

    void addEdge(char x, char y) {
        addEdgeInList(x, y);
    }

    void getArticulationPoints() {
        System.out.print("Articulation Points: ");
        for (Node node : adj) {
            if (!node.isVisited())
                dfsArticulation(node);
        }
        for (int i = 0; i < adj.size(); i++) {
            if (isArticulation[i])
                System.out.print(adj.get(i).getValue() + " ");
        }
        System.out.println();
    }

    void printGraph() {
        System.out.println("Graph: ");
        for (Node node : adj)
            System.out.println(node);
    }

    private void dfsArticulation(Node node) {
        int children = 0;
        int nodeIndex = adj.indexOf(node);

        node.setDiscoveryTime(time);
        node.setLowValue(time);
        time++;
        node.setVisited();

        for (Node neighbour : node.getNeighbours()) {
            if (!neighbour.isVisited()) {
                children++;
                neighbour.setParent(node);

                dfsArticulation(neighbour);

                node.setLowValue(Math.min(node.getLowValue(), neighbour.getLowValue()));

                if (node.getParent() == null && children > 1)
                    isArticulation[nodeIndex] = true;
                if (node.getParent() != null && neighbour.getLowValue() >= node.getDiscoveryTime())
                    isArticulation[nodeIndex] = true;
            }
            else if (neighbour != node.getParent())
                node.setLowValue(Math.min(node.getLowValue(), neighbour.getDiscoveryTime()));
        }
    }

    private void addEdgeInList(char x, char y) {
        Node nodeX = getNode(x);
        if (nodeX == null) {
            nodeX = new Node(x);
            adj.add(nodeX);
        }
        Node nodeY = getNode(y);
        if (nodeY == null) {
            nodeY = new Node(y);
            adj.add(nodeY);
        }
        nodeX.addNeighbour(nodeY);
        nodeY.addNeighbour(nodeX);
    }

    private Node getNode(char value) {
        for (Node node : adj) {
            if (node.getValue() == value)
                return node;
        }
        return null;
    }

    private void resetVisited() {
        for (Node node : adj)
            node.reset();
    }
}

public class ArticulationPoint{
	public static void main(String [] args){
		Graph graph = new Graph(5);
		graph.addEdge('a', 'b');
		graph.addEdge('b', 'a'); 
		graph.addEdge('b', 'c');
		graph.addEdge('c', 'd');
		graph.addEdge('d', 'c'); 
		graph.addEdge('e', 'f');
		graph.addEdge('f', 'e'); 
		graph.addEdge('d', 'e'); 
        graph.printGraph();
        graph.getArticulationPoints();
	}
}