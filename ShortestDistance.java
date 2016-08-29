import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

/**
 * This is the class that has the main method to run graph searches
 * 
 * @author William Gu
 * @version 1.0
 */
public class ShortestDistance {
  
  /**
   * The main method for the class
   */
  public static void main(String[] args) {
    GraphSearch graph = new GraphSearch();
    Map<String, List<Pair<String, Integer>>> map = 
      new HashMap<String, List<Pair<String, Integer>>>();
    List<Pair<String, Integer>> list;
    Pair<String, Integer> pair;
    String line;
    boolean repeat = true;
    Scanner sc = new Scanner(System.in);

    if (args.length != 1) {
      System.out.println("Error, the command should be:");
      System.out.println("java ShortestDistance filename");
      return;
    }

    try {
      FileReader fileReader = new FileReader(args[0]);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      //Loop reads in each line
      while((line = bufferedReader.readLine()) != null) {
        //Split line into array of strings by separating a space
        String[] tokens = line.split(" "); 
        String src = tokens[0];
        String dst = tokens[1];
        int distance = Integer.parseInt(tokens[2]);

        //Sees if the source node has edges or not
        if (map.containsKey(src)) {
          list = map.get(src); 
        } else {
          list = new ArrayList<Pair<String, Integer>>();  
        }

        //Updates the list with new edge
        pair = new Pair<String, Integer>(dst, distance);
        list.add(pair);
        map.put(src, list);
      } 

    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file.");
      return;
    } catch (IOException ex) {
      System.out.println("Error reading file.");
      return;
    }
    // Asks the user to enter locations to find shortest path
    while (repeat) {
      System.out.print("Enter the starting location: ");
      String start = sc.nextLine();
      System.out.print("Enter the ending location: ");
      String end = sc.nextLine();

      if (!map.containsKey(start)) {
        System.out.println("Starting location does not exist.");
      } else if (graph.djikstraShortestPathAlgorithm(start, map, end) == -1) {
        System.out.println("Ending location cannot be reached or does not exist.");
      } else {
        System.out.println("The shortest distance from " + start + " to "
        + end + " is " + graph.djikstraShortestPathAlgorithm(start, map, end));
      }

      System.out.print("Enter new locations(y/n)? ");
      if (!sc.nextLine().equals("y")) {
        System.out.println("Good bye.");
        repeat = false;
      } 
    }
  }

}
