import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.io.File;  
import java.io.FileNotFoundException;


class Graph{

    private int numberOfPoint = 0;
    private int numberOfEdges = 0;
    private List<List<Integer>> adj;

    Graph(String filename){
        try {
            File object = new File(filename);
            Scanner myReader = new Scanner(object);
            boolean isFirstLine = true;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                if(isFirstLine){
                    this.numberOfPoint = x;
                    this.numberOfEdges = y;
                    adj = new ArrayList<>(numberOfPoint);
                    for (int i = 0; i < numberOfEdges; i++) {
                        adj.add(new ArrayList<>());
                    }
                    isFirstLine = false;
                } else {
                    addEdge(x, y);
                }
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("idk man");
          }
    }

    void displayGraph(){
        int idx = 0;
        for(List<Integer> a : adj){
            System.out.print(idx + " -> ");
            for(int i : a)
                System.out.print(i + " ");
            System.out.println();
            idx++;
        }
    }

    void DFS(int src){
        boolean[] visited = new boolean[numberOfPoint];
        choloDFSkori(visited, src);
        System.out.println();
    }

    List<Integer> topologicalSort(){
        boolean[] visited = new boolean[numberOfPoint];
        Stack<Integer> resultStack = new Stack<>();
        for (int node = 0; node < numberOfPoint; node++) {
            if (!visited[node]){
                topKori(visited, node, resultStack);
            }
        }
        List<Integer> result = new ArrayList<>(resultStack.size());
        while (!resultStack.empty()) {
            result.add((Integer) resultStack.pop());
        }
        return result;
    }

    private void addEdge(int x, int y){
        adj.get(x).add(y);
    }

    private void choloDFSkori(boolean[] visited, int idx){
        visited[idx] = true;
        System.out.print(idx + " ");
        for(int node : adj.get(idx)){
            if(!visited[node]){
                choloDFSkori(visited, node);
            }
        }
    } 

    private void topKori(boolean[] visited, int idx, Stack<Integer> result){
        visited[idx] = true;
        for(int node : adj.get(idx)){
            if(!visited[node]){
                topKori(visited, node, result);
            }
        }
        result.push(idx);
    } 
}




public class CSE2212LabTwo {
    public static void main(String [] args){ 
        Graph graph = new Graph("input.txt");

        System.out.println("Graph adjacency list:");
        graph.displayGraph();

        System.out.println("\nPerforming DFS starting from vertex 5:");
        graph.DFS(5);

        System.out.println("\nPerforming Topological Sort:");
        List<Integer> topoOrder = graph.topologicalSort();
        System.out.println("Topological Sort order: " + topoOrder);
    }
}