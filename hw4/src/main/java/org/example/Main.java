//package org.example;
//import java.util.*;
//
//public class Main {
//    private static int[][] graph;
//    private static int numVertices;
//    private static int startVertex;
//    private static List<List<Integer>> bestPaths = new ArrayList<>();
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Read input graph data
//        numVertices = scanner.nextInt();
//        int numEdges = scanner.nextInt();
//        graph = new int[numVertices][numVertices];
//
//        for (int i = 0; i < numEdges; i++) {
//            int source = scanner.nextInt();
//            int destination = scanner.nextInt();
//            int cost = scanner.nextInt();
//            graph[source][destination] = cost;
//        }
//
//        startVertex = scanner.nextInt();
//        scanner.close();
//
//        List<Integer> currentPath = new ArrayList<>();
//        boolean[] visited = new boolean[numVertices];
//        currentPath.add(startVertex);
//        visited[startVertex] = true;
//
//        int minCost = tsp(startVertex, 0, visited, currentPath, 0);
//
//        if (minCost == Integer.MAX_VALUE) {
//            System.out.println("Path:");
//            System.out.println("Cost:-1");
//        } else {
//            System.out.println("Path(s) with shortest cost:");
//            for (List<Integer> path : bestPaths) {
//                for (int vertex : path) {
//                    System.out.print(vertex + "->");
//                }
//                System.out.println(startVertex);
//            }
//            System.out.println("Cost:" + minCost);
//        }
//    }
//
//    private static int tsp(int currentVertex, int count, boolean[] visited, List<Integer> currentPath, int cost) {
//        System.out.println("=======================tsp start============================");
//        if (count == numVertices - 1) {
//            if (graph[currentVertex][startVertex] != 0) {
//                int totalCost = cost + graph[currentVertex][startVertex];
//                savePathAndCost(currentPath, totalCost);
//                return totalCost;
//            } else {
//                return Integer.MAX_VALUE;
//            }
//        }
//        System.out.println("=======================tsp mid============================");
//        int minCost = Integer.MAX_VALUE;
//        for (int nextVertex = 0; nextVertex < numVertices; nextVertex++) {
//            if (!visited[nextVertex] && graph[currentVertex][nextVertex] != 0) {
//                visited[nextVertex] = true;
//                currentPath.add(nextVertex);
//                System.out.println("=======================tsp recurse============================");
//                int result = tsp(nextVertex, count + 1, visited, currentPath, cost + graph[currentVertex][nextVertex]);
//                minCost = Math.min(minCost, result);
//                visited[nextVertex] = false;
//                currentPath.remove(currentPath.size() - 1);
//            }
//        }
//        System.out.println("=======================tsp return============================");
//        return minCost;
//    }
//
//    private static void savePathAndCost(List<Integer> path, int cost) {
//        if (bestPaths.isEmpty() || cost < getCost(bestPaths.get(0))) {
//            bestPaths.clear();
//            bestPaths.add(new ArrayList<>(path));
//        } else if (cost == getCost(bestPaths.get(0))) {
//            bestPaths.add(new ArrayList<>(path));
//        }
//    }
//
//    private static int getCost(List<Integer> path) {
//        int cost = 0;
//        for (int i = 0; i < path.size() - 1; i++) {
//            cost += graph[path.get(i)][path.get(i + 1)];
//        }
//        return cost;
//    }
//}
//
package org.example;
import java.util.*;

public class Main {
    private static int[][] graph;
    private static int numVertices;
    private static int startVertex;
    private static List<List<Integer>> bestPaths = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input graph data
        numVertices = scanner.nextInt();
        int numEdges = scanner.nextInt();
        graph = new int[numVertices][numVertices];

        // Print the graph data read
        System.out.println("Input Graph:");
        for (int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int cost = scanner.nextInt();
            graph[source][destination] = cost;
        }

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(graph[i][j] + "\t");
            }
            System.out.println();
        }

        startVertex = scanner.nextInt();
        scanner.close();

        List<Integer> currentPath = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];
        currentPath.add(startVertex);
        visited[startVertex] = true;

        int minCost = tsp(startVertex, 0, visited, currentPath, 0);

        if (minCost == Integer.MAX_VALUE) {
            System.out.println("Path:");
            System.out.println("Cost:-1");
        } else {
            System.out.println("Paths with shortest cost:");
            for (List<Integer> path : bestPaths) {
                for (int vertex : path) {
                    System.out.print(vertex + "->");
                }
                System.out.println(startVertex);
            }
            System.out.println("Cost:" + minCost);
        }
    }

    private static int tsp(int currentVertex, int count, boolean[] visited, List<Integer> currentPath, int cost) {
        System.out.println("=======================tsp start============================");
//        System.out.println("Current Vertex: " + currentVertex);
//        System.out.println("Count: " + count);
//        System.out.println("Visited: " + Arrays.toString(visited));
//        System.out.println("Current Path: " + currentPath);
//        System.out.println("Cost: " + cost);
        if (count == numVertices - 1) {
            if (graph[currentVertex][startVertex] != 0) {
                int totalCost = cost + graph[currentVertex][startVertex];
                savePathAndCost(currentPath, totalCost);
                System.out.println("Current Path: " + currentPath);
                System.out.println("total cost" + totalCost);
                return totalCost;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        System.out.println("=======================tsp mid============================");
        int minCost = Integer.MAX_VALUE;
        for (int nextVertex = 0; nextVertex < numVertices; nextVertex++) {
            if (!visited[nextVertex] && graph[currentVertex][nextVertex] != 0) {
                visited[nextVertex] = true;
                currentPath.add(nextVertex);
                System.out.println("=======================tsp recurse============================");
                int result = tsp(nextVertex, count + 1, visited, currentPath, cost + graph[currentVertex][nextVertex]);
                minCost = Math.min(minCost, result);
                visited[nextVertex] = false;
                currentPath.remove(currentPath.size() - 1);
            }
        }
        System.out.println("=======================tsp return============================");
        System.out.println("Min Cost: " + minCost);
        return minCost;
    }

    private static void savePathAndCost(List<Integer> path, int cost) {
        if (bestPaths.isEmpty() || cost < getCost(bestPaths.get(0))) {
            bestPaths.clear();
            bestPaths.add(new ArrayList<>(path));
        } else if (cost == getCost(bestPaths.get(0))) {
            System.out.println("cost: " + cost + "    getCost: " + getCost(bestPaths.get(0)));
            bestPaths.add(new ArrayList<>(path));
        }
    }
// FIX ISSUE: getcost() compares without the cost going back to source node.  compared to full path in savePathAndCost()
    private static int getCost(List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph[path.get(i)][path.get(i + 1)];
        }
        return cost;
    }
}

