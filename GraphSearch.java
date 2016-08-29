import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the class that has the different graph searches
 * 
 * @author William Gu
 * @version 1.0
 */
public class GraphSearch {

  /**
   * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
   * using General Graph Search.
   *
   * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
   *
   * The structure(struct) passed in is an empty structure may behave as a Stack or Queue and the
   * correspondingly search function should execute DFS(Stack) or BFS(Queue) on the graph.
   *
   * @param start
   * @param struct
   * @param adjList
   * @param goal
   * @return true if path exists false otherwise
   */
  public static <T> boolean generalGraphSearch(T start, Structure<T> struct, Map<T, List<T>> adjList, T goal) {
    //Check if there are any illegal values
    if (start == null | struct == null | adjList == null | goal == null) {
      throw new IllegalArgumentException();
    }

    StructureQueue<T> traversalOrder = new StructureQueue<T>();
    Set<T> visited = new HashSet<T>();
    T topVertex;
    T nextNeighbor;
    List<T> adj;

    //First node is marked
    visited.add(start);
    traversalOrder.add(start);
    struct.add(start);

    //Loops through structure 
    while (!struct.isEmpty()) {
      topVertex = struct.remove();
      adj = adjList.get(topVertex);
      //Loops through adjacency list(edges from current node) if not null
      if (adj != null) {
        for (int i = 0; i < adj.size(); i++) {
          //Checks if node has been visited, otherwise gets added 
          if (!visited.contains(adj.get(i))) {
            nextNeighbor = adj.get(i);
            if (nextNeighbor.equals(goal)) {
              return true;
            }
            visited.add(nextNeighbor);
            traversalOrder.add(nextNeighbor);
            struct.add(nextNeighbor);
          } 
        }
      }

    }
    return false;
  }
 
  /**
   * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
   * using Bredth First Search.
   *
   * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
   *
   * @param start
   * @param adjList
   * @param goal
   * @return true if path exists false otherwise
   */
  public static <T> boolean breadthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
    return generalGraphSearch(start, new StructureQueue<T>(), adjList, goal);
  }
 
  /**
   * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
   * using Depth First Search.
   *
   * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
   *
   * @param start
   * @param adjList
   * @param goal
   * @return true if path exists false otherwise
   */
  public static <T> boolean depthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
    return generalGraphSearch(start, new StructureStack<T>(), adjList, goal);
  }
 
  /**
   * Find the shortest distance between the start node and the goal node in the given a weighted graph
   * in the form of an adjacency list where the edges only have positive weights
   * Return the aforementioned shortest distance if there exists a path between the start and goal,-1
   * otherwise.
   *
   * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
   * There are no negative edge weights in the graph.
   *
   * @param start
   * @param adjList
   * @param goal
   * @return the shortest distance between the start and the goal node
   */
  public static <T> int djikstraShortestPathAlgorithm(T start, Map<T, List<Pair<T, Integer>>> adjList, T goal) {
    //Check for illegal values
    if (start == null | adjList == null | goal == null) {
      throw new IllegalArgumentException();
    }
    //This Comparator is used so Pair objects can be compared in the priority queue
    Comparator<Pair<T, Integer>> comparator = new Comparator<Pair<T, Integer>>() { 
      public int compare(Pair<T, Integer> sg1, Pair<T, Integer> sg2) {
        return sg1.getB() - sg2.getB();
      }
    };

    List<Pair<T, Integer>> adj;
    Set<T> visited = new HashSet<T>();
    //Priority queue is used to sort elements and put lowest element on top
    PriorityQueue<Pair<T, Integer>> traversal = new PriorityQueue<Pair<T, Integer>>(11, comparator);
    Pair<T, Integer> pair = new Pair<T, Integer>(start, 0);
    T topVertex;
    int nextCost;
    Pair<T, Integer> nextNeighbor;

    //First pair containing starting node is added
    traversal.add(pair);

    //Loops through priority queue
    while (!traversal.isEmpty()) {
      pair = traversal.remove();
      topVertex = pair.getA();
      adj = adjList.get(topVertex);
      //Checks if node has been visited, otherwise gets added 
      if (!visited.contains(topVertex)) {
        visited.add(topVertex);
        if (topVertex.equals(goal)) {
          return pair.getB();
        } else {
          //Loops through adjacency list(edges from current node) if not null
          if (adj != null) {
            for (int i = 0; i < adj.size(); i++) {
              nextNeighbor = adj.get(i);
              if (!visited.contains(nextNeighbor.getA())) {
                //Adds the total distance and puts it into the priority queue as a pair
                nextCost = pair.getB() + nextNeighbor.getB();
                traversal.add(new Pair<T, Integer>(nextNeighbor.getA(), nextCost));
              }
            }
          }
        }
      }
    }
    //If priority queue did not find target then -1 is returned 
    return -1;
  }   

}