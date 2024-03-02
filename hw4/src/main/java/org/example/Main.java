package org.example;
import java.util.*;

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

        for (int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int cost = scanner.nextInt();
            graph[source][destination] = cost;
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
            System.out.println("Path(s) with shortest cost:");
            for (List<Integer> path : bestPaths) {
                for (int vertex : path) {
                    System.out.print(vertex + "->");
                }
                System.out.println(startVertex); // Complete the loop
            }
            System.out.println("Cost:" + minCost);
        }
    }

    private static int tsp(int currentVertex, int count, boolean[] visited, List<Integer> currentPath, int cost) {
        if (count == numVertices - 1) {
            if (graph[currentVertex][startVertex] != 0) {
                int totalCost = cost + graph[currentVertex][startVertex];
                savePathAndCost(currentPath, totalCost);
                return totalCost;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int nextVertex = 0; nextVertex < numVertices; nextVertex++) {
            if (!visited[nextVertex] && graph[currentVertex][nextVertex] != 0) {
                visited[nextVertex] = true;
                currentPath.add(nextVertex);
                int result = tsp(nextVertex, count + 1, visited, currentPath, cost + graph[currentVertex][nextVertex]);
                minCost = Math.min(minCost, result);
                visited[nextVertex] = false;
                currentPath.remove(currentPath.size() - 1);
            }
        }
        return minCost;
    }

    private static void savePathAndCost(List<Integer> path, int cost) {
        if (bestPaths.isEmpty() || cost < getCost(bestPaths.get(0))) {
            bestPaths.clear();
            bestPaths.add(new ArrayList<>(path));
        } else if (cost == getCost(bestPaths.get(0))) {
            bestPaths.add(new ArrayList<>(path));
        }
    }

    private static int getCost(List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph[path.get(i)][path.get(i + 1)];
        }
        return cost;
    }
}

//public class Main {
//    private static int[][] graph;
//    private static int numVertices;
//    private static int startVertex;
//    private static List<Integer> bestPath;
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
//        // Perform TSP and print result
//        bestPath = new ArrayList<>();
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
//            System.out.println("Path:");
//            for (int vertex : bestPath) {
//                System.out.print(vertex + "->");
//            }
//            System.out.println(startVertex); // Complete the loop
//            System.out.println("Cost:" + minCost);
//        }
//    }
//
//    private static int tsp(int currentVertex, int count, boolean[] visited, List<Integer> currentPath, int cost) {
//        System.out.println("Current Path: " + currentPath);
//        System.out.println("Current Cost: " + cost);
//
//        if (count == numVertices - 1) {
//            if (graph[currentVertex][startVertex] != 0) {
//                currentPath.add(startVertex);
//                if (cost + graph[currentVertex][startVertex] < getCost(bestPath)) {
//                    bestPath = new ArrayList<>(currentPath);
//                }
//                currentPath.remove(currentPath.size() - 1);
//                return cost + graph[currentVertex][startVertex];
//            } else {
//                return Integer.MAX_VALUE;
//            }
//        }
//
//        int minCost = Integer.MAX_VALUE;
//        for (int nextVertex = 0; nextVertex < numVertices; nextVertex++) {
//            if (!visited[nextVertex] && graph[currentVertex][nextVertex] != 0) {
//                visited[nextVertex] = true;
//                currentPath.add(nextVertex);
//                int result = tsp(nextVertex, count + 1, visited, currentPath, cost + graph[currentVertex][nextVertex]);
//                if (result < minCost) {
//                    minCost = result;
//                }
//                if (currentPath.size() == numVertices) { // Save path and cost when current path has 4 elements
//                    savePathAndCost(currentPath, minCost);
//                }
//                visited[nextVertex] = false;
//                currentPath.remove(currentPath.size() - 1);
//            }
//        }
//        return minCost;
//    }
//
//    private static void savePathAndCost(List<Integer> path, int cost) {
//        bestPath = new ArrayList<>(path);
//        System.out.println("Saved Path: " + bestPath);
//        System.out.println("Saved Cost: " + cost);
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
