About the Project:
This project involves implementing different types of graph searches and applying one graph search method for the shortest path computation. In the program a weighted directed graph is created where the cities are the nodes, and the program lets the user know what is the shortest distance from one city to another. The way the program does this is by using dijkstra's algorithm which calculates the shortest path by traversing the paths and getting the minimum result using a priority queue. The graph is initially created by reading a text file where each line is a weighted directed edge with the first column as the head, second column as the tail, and third column as the length. The nodes do not have to represent cities and can be used for other situations to find the shortest path.

Instructions for the command line:
1) To compile the files type
   make
2) To delete the executables type
   make clean
3) To run the program type 
   java ShortestDistance cities.txt 
  or
   java ShortestDistance graph.txt
4) When running the program, type the start and end location to find the shortest path.
   
