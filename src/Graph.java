import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    private final int vertices; // represents the number of vertices in the graph
    private final ArrayList<LinkedList<Integer>> edges; // representation of the edges in the graph as an adjacency list
    private String s; // this is the string representation of the graph
    private int nextLine = 0; // this is used to determine when to go to a new line when displaying the edges to screen


    // constructs a graph with the inputted number of vertices and no edges
    public Graph(int vertices) {
        this.vertices = vertices;
        // there is a list in edges for each vertex in the graph, which will contain the vertices it shares an edge with
        edges = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            edges.add(new LinkedList<>());
        }
        s = "The graph has " + vertices + " vertices.\n" + "The edges of the graph are:\n";
    }


    // adds an edge to the graph
    // it is assumed that there are no duplicate edges added to the graph and the vertices are numbered 0 to this.vertices
    public void addEdge(Integer vertex1, Integer vertex2) {
        nextLine += 1;
        edges.get(vertex1).add(vertex2);
        edges.get(vertex2).add(vertex1);
        s += "(" + vertex1 + ", " + vertex2 + ") "; // we add the new edge to the string representation of the graph
        if (nextLine == 10) {
            s += "\n";
            nextLine = 0;
        }
    }


    // return the number of vertices in the graph
    public int getVertices() {
        return vertices;
    }


    // returns the edges in the graph
    public ArrayList<LinkedList<Integer>> getEdges() {
        return edges;
    }


    @Override
    public String toString() {
        return s;
    }

}
