import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Test {
    /*
    This class can check the running time of the vertex cover algorithms. The checks can be run for either default
    graphs or user inputted graphs.
     */


    // this method takes a graph and int k as input and prints out the running time and vertex covers of bruteForce
    // and approximate
    private static void testing(Graph G, int k) {
        // running the brute force cover algorithm
        Duration dur;
        Instant start = Instant.now();
        ArrayList<Integer> bruteVC = VertexCover.bruteForceCover(G, k);
        Instant end = Instant.now();
        dur = Duration.between(start,end);
        System.out.println("Brute Force: \n" + "The running time was: " + dur.toMillis() + "ms");
        if (bruteVC.isEmpty()) {
            System.out.println("There is no vertex cover of size " + k + ".");
        } else {
            System.out.println("The vertex cover is: " + bruteVC);
        }

        // running the approximate cover algorithm
        start = Instant.now();
        ArrayList<Integer> approxVC = VertexCover.approximateCover(G);
        end = Instant.now();
        dur = Duration.between(start,end);
        System.out.println("Approximate: \n" + "The running time was: " + dur.toMillis() + "ms");
        System.out.println("The approximate vertex cover is: " + approxVC + "\n");
    }


    // brute force and approximate running times and resulting vertex covers are found for the pre-set test graphs and
    // cover sizes
    public static void defaultGraphsTest() {
        System.out.println("We test the algorithms on default graphs.");
        Graph g1 = new Graph(10);
        g1.addEdge(0,4);
        g1.addEdge(0,6);
        g1.addEdge(1,2);
        g1.addEdge(2,3);
        g1.addEdge(2,4);
        g1.addEdge(3,6);
        g1.addEdge(5,8);
        g1.addEdge(7,8);
        g1.addEdge(8,9);
        System.out.println("Graph 1");
        System.out.println(g1);
        System.out.println("\nInput k = 3");
        testing(g1, 3);
        System.out.println("Input k = 5");
        testing(g1, 5);

        System.out.println("\n\nGraph 2");
        Graph g2 = new Graph(20);
        g2.addEdge(0,4);
        g2.addEdge(0,6);
        g2.addEdge(1,2);
        g2.addEdge(2,3);
        g2.addEdge(2,4);
        g2.addEdge(3,6);
        g2.addEdge(4,10);
        g2.addEdge(5,8);
        g2.addEdge(5,10);
        g2.addEdge(7,8);
        g2.addEdge(8,9);
        g2.addEdge(10,14);
        g2.addEdge(11,12);
        g2.addEdge(11,15);
        g2.addEdge(12,15);
        g2.addEdge(12,16);
        g2.addEdge(13,16);
        g2.addEdge(13,14);
        g2.addEdge(14,17);
        g2.addEdge(14,18);
        g2.addEdge(15,19);
        g2.addEdge(16,17);
        g2.addEdge(18,19);
        System.out.println(g2);
        System.out.println("\nInput k = 5");
        testing(g2, 5);
        System.out.println("Input k = 10");
        testing(g2, 10);
    }


    // prints brute force and approximate running times and resulting vertex cover for user inputted graph and cover size
    // it is assumed the user properly inputs the number of vertices and the edges of the graph
    public static void userGraphTest() {
        Scanner userInput = new Scanner(System.in);
        // we get the number of vertices from the user and set up the graph
        System.out.println("How many vertices 'n' are in the graph?");
        int vertices = userInput.nextInt();
        userInput.nextLine(); // gets rid of the remainder of the line after the int that was entered
        Graph g = new Graph(vertices);

        // we add user edges to the graph until the ending string '0,0' is entered and all edges have been added
        System.out.println("Numbering the graph vertices 0 through n, enter an edge on each line with the form 'u,v'." +
                " If there are no more edges to input enter '0,0'.");
        boolean stop = false;
        while (!stop) {
            String input = userInput.nextLine();
            if (Objects.equals(input, "0,0")) {
                stop = true;
            } else {
                ArrayList<String> edge = new ArrayList<>(Arrays.asList(input.split(",")));
                g.addEdge(new Integer(edge.get(0)), new Integer(edge.get(1)));
            }
        }

        // we get the value k, the size for which we want to know if there is a vertex cover
        System.out.println("Enter integer k, for which to find a possible vertex cover.");
        int k = userInput.nextInt();
        userInput.nextLine(); // gets rid of the remainder of the line after the int that was entered
        testing(g, k); // runs the algorithms on the user's graph
    }

}
