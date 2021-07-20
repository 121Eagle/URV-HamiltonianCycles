package example1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Integer;
import java.util.*;
// A class to store a graph edge
class Edge
{
    int source, dest;
    public Edge(int source, int dest)
    {
        this.source = source;
        this.dest = dest;
    }
}
// A class to represent a graph object
class Graph
{
    // A list of lists to represent an adjacency list
    List<List<Integer>> adjList = null;
    // Constructor
    Graph(List<Edge> edges, int N)
    {
        adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
        // add edges to the undirected graph
        for (Edge edge: edges)
        {
            int src = edge.source;
            int dest = edge.dest;
            if(!adjList.get(src).contains(dest)) {
            	adjList.get(src).add(dest);
            }
            if(!adjList.get(dest).contains(src)) {
            	adjList.get(dest).add(src);
            }
        }
    }
}
public class Main
{
	  private static List<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    public static void printAllHamiltonianPaths(Graph g,
                                                int v,
                                                boolean[] visited,
                                                ArrayList<Integer> path,
                                                int N) {
        // if all the vertices are visited, then the Hamiltonian path exists
        if (path.size() == N)
        {
              if(g.adjList.get(v).contains(0)){
                path.add(0);
              System.out.println(path);
              }
            // print the Hamiltonian path
        /*	if(!paths.contains(path)) {
        		paths.add(path);
        	}
              //System.out.println(path);
        	System.out.println(paths); */
            return;
        }
        // Check if every edge starting from vertex `v` leads
        // to a solution or not
        for (int w: g.adjList.get(v))
        {
            // process only unvisited vertices as the Hamiltonian
            // path visit each vertex exactly once
            if (!visited[w])
            {
                visited[w] = true;
                path.add(w);
                // check if adding vertex `w` to the path leads
                // to the solution or not
                printAllHamiltonianPaths(g, w, visited, path, N);
                // backtrack
                visited[w] = false;
                path.remove(path.size() - 1);
            }
        }
    }
    //works
public static boolean differences(int[]arr1, int[]arr2){
  ArrayList<Integer> diff = new ArrayList<Integer> ();
  for(int i = 0; i<arr1.length; ++i){
    if(arr1[i]!=arr2[i]){
    	diff.add(i);
    }
  }
  return diff.size() == 2 && diff.get(1) == 1 + diff.get(0);
}
//MAIN FUNCTION WHICH AIMS TO GENERATE LIST OF EDGES
    public static List<Edge> generateEdges(int n){
      int [] arr = new int[n];
      for(int i = 0; i < arr.length; ++i){
          arr[i] = i+1;
        }
      ArrayList<Integer> permutations = new ArrayList<Integer>();
      helperGeneratePermutation(arr, 0, permutations);
      //System.out.println(permutations);
         Collections.sort(permutations);
     List<List<Integer>> adjList = new ArrayList<List<Integer>>();
     for(int i = 0; i < permutations.size(); ++i ){
        List<Integer> neighbors = new ArrayList<Integer>();
       for(int j = 0; j<permutations.size();++j){
         if((differences(intSplit(permutations.get(i)) , intSplit(permutations.get(j)) ))){
           neighbors.add(j);
         }
       }
       adjList.add(neighbors);
     }
    System.out.println(adjList);
    //make edges list by flattening adjacency list
    //canonically order adjacency list
      List<Edge> edges = new ArrayList<Edge>();
    System.out.println(permutations);
    for(int i = 0; i < adjList.size(); ++i){
      for(int j = 0; j < adjList.get(i).size(); ++j){
        edges.add(new Edge(i,adjList.get(i).get(j)));
      }
    }
//wrap in private recursive method
    //System.out.println(edges);
      return edges;
    }
  //works
    public static int[] intSplit(int a){
      ArrayList<Integer> digits = new ArrayList<Integer>();
      while(a!=0){
        digits.add(a%10);
        a = a/10;
      }
      //return digits.<Integer>toArray(new int[digits.size()]);
      int returns[] = new int[digits.size()];
      for(int i=(digits.size() - 1); i>=0; i--){
        returns[i] = digits.get(i).intValue();
      }
      digits = null;
      return returns;
    }
  //works
    public static int toInteger(int[]n){
      int powerOf10 = 0;
      int res = 0;
      for(int i = n.length-1; i>=0; i--){
        res += n[i]*Math.pow(10, powerOf10);
        powerOf10++;
      }
      return res;
    }
    public static void swap(int[]arr, int i, int idx){
      int temp = arr[i];
      arr[i] = arr[idx];
      arr[idx] = temp;
    }
//GENERATES PERMUTATIONS
    public static void helperGeneratePermutation(int []n, int cidx, ArrayList<Integer>permutations){
      if(cidx == n.length-1){
        permutations.add(toInteger(n));
        return;
      }
       for(int i = cidx; i < n.length; ++i){
        swap(n,i,cidx);
        helperGeneratePermutation(n,cidx+1, permutations);
        swap(n,i,cidx);
      }
    }
    public static void main(String[] args)
    {
     // System.out.println(generateEdges(3));
        // consider a complete graph having 4 vertices
        /*List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(1, 2), new Edge(2, 3),
                new Edge(3, 4), new Edge(4, 5), new Edge(5, 0),
                new Edge(6,7),  new Edge(7,8), new Edge(8,9), new Edge(9,10), new Edge(10,11),
                new Edge(11,6), new Edge(12,13), new Edge(13,14), new Edge(14,15), new Edge(15,16),new Edge(16,17),new Edge(17,12), new Edge(18,19),new Edge(19,20),new Edge(20,21),new Edge(21,22),new Edge(22,23),new Edge(23,18),new Edge(0,6), new Edge(1,7),new Edge(2,18),new Edge(3,19),new Edge(4,13),new Edge(5,12),new Edge(11,17),new Edge(10,16),new Edge(9,22),new Edge(8,23),new Edge(14,20),new Edge(15,21)
        );*/
        List<Edge> edges2 = generateEdges(4);
        // total number of nodes in the graph
        final int N = 24;
        // build a graph from the given edges
        Graph g = new Graph(edges2, N);
        // starting node
        int start = 0;
        // add starting node to the path
        ArrayList<Integer> path = new ArrayList<>();
        path.add(start);
        // mark the start node as visited
        boolean[] visited = new boolean[N];
        visited[start] = true;
        printAllHamiltonianPaths(g, start, visited, path, N);
       // System.out.println(paths);
    }
}