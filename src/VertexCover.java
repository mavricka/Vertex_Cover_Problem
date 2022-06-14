import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class VertexCover {
    /*
    This class has only static methods and is meant to return a vertex cover of inputted Graphs using the
    bruteForceCover or approximateCover methods.
     */


    // given the number of vertices to choose from n and the size of the subset k, we return all combinations of k vertices
    // each element in the returned ArrayList is an array of length n, where a 1 means it's included in the combination
    private static ArrayList<int[]> combinations(int n, int k) {
        ArrayList<int[]> combs = new ArrayList<>();

        int[] comb = new int[k]; // this is an array of length k, where each value represents a vertex in the combination
        // this is our first combination, the first k vertices
        for (int i = 0; i < k; i++) {
            comb[i] = i;
        }
        // here we generate the rest of the combinations and add them to the list
        while (comb[k-1] < n) {
            // we add the latest combination to the list
            int[] combination = new int[n]; // initialized with all values as 0
            for (int i: comb) {
                combination[i] = 1;
            }
            combs.add(combination);

            // we generate the next combination
            int j = k - 1;
            while (j != 0 && comb[j] == n - k + j) {
                j--;
            }
            comb[j]++;
            for (int i = j + 1; i < k; i++) {
                comb[i] = comb[i - 1] + 1;
            }
        }
        return combs;
    }


    // returns a vertex cover of size k, if there is no vertex cover of size k an empty list is returned
    public static ArrayList<Integer> bruteForceCover(Graph G, Integer k) {
        ArrayList<Integer> cover = new ArrayList<>();
        ArrayList<LinkedList<Integer>> edges = G.getEdges(); // an adjacency list representation of the edges of G
        ArrayList<int[]> combs = combinations(G.getVertices(), k); // this gives us all possible combination of k vertices

        // for each combination of vertices in combs we check if it is a valid vertex cover
        for (int[] possibleCover: combs) {
            // note each possibleCover array has length equal to the number of vertices of G
            // if possibleCover[i] == 1, then vertex i is in this possible cover
            // we go through all the edges and check if they are covered by possibleCover
            boolean isCover = true;
            for (int u = 0; u < G.getVertices(); u++) {
                if (possibleCover[u] == 1) {
                    // u is in the possible cover so all of its edges will be (and don't need to be checked)
                    continue;
                }
                for (int v: edges.get(u)) {
                    // we know u is not in the cover, so the edge (u,v) is only covered if v is in the possible cover
                    if (possibleCover[v] != 1) {
                        // v is not in the cover, thus the edges (u,v) is not covered and this combination of vertices is not a cover
                        isCover = false;
                        break;
                    }
                }
                if (!isCover) {
                    break; // an edge was found to not be covered, so we don't need to continue checking edges since this is not a cover
                }
            }
            if (isCover) {
                // this is a valid cover so we will return it
                for (int i = 0; i < possibleCover.length; i++) {
                    if (possibleCover[i] == 1) {
                        cover.add(i); // if the vertex i has been marked as 1 in covered, it is in the vertex cover and is added
                    }
                }
                return cover; // we already found a cover of size k, so no need to check for more
            }
        }
        return cover; // this returns an empty ArrayList if there was no valid vertex cover of size k
    }


    // returns an approximate minimum vertex cover of G
    public static ArrayList<Integer> approximateCover(Graph G) {
        int[] covered = new int[G.getVertices()]; // if a vertex x is in the cover, covered[x] == 1 and otherwise 0
        ArrayList<LinkedList<Integer>> edges = G.getEdges(); // note edges are represented in an adjacency list, where each vertex v in edges[u] share an edge with u

        // we loop through vertices 0 to G.getVertices(), choosing the first uncovered edge to be in the vertex cover
        for (int u = 0; u < G.getVertices(); u++) {
            if (covered[u] == 1) {
                continue; // u has already been added to the vertex cover in this case, so all edges with u are covered already
            }
            // for each edge x shared with u, we loop until we find the first edge (u,vertex) that is not covered to added to the cover
            for (int v: edges.get(u)) {
                if (covered[v] == 0) {
                    // we have found an uncovered vertex (u,v) and will add u,v to the cover
                    covered[u] = 1;
                    covered[v] = 1;
                    break; // we don't look through the rest of the edges with u since they are now covered
                }
            }
        }
        ArrayList<Integer> cover = new ArrayList<>(); // this will be our returned vertex cover
        for (int i = 0; i < covered.length; i++) {
            if (covered[i] == 1) {
                cover.add(i); // if the vertex i has been marked as 1 in covered, it is in the vertex cover and is added
            }
        }
        return cover;
    }

}
