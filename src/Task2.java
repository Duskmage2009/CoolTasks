import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of test cases
        int testCases = readIntWithValidation(sc, "Enter number of test cases (<= 10): ", 1, 10);

        while (testCases-- > 0) {
            int n = readIntWithValidation(sc, "Enter number of cities (<= 10000): ", 1, 10000);
            Map<String, Integer> cityIndex = new HashMap<>();
            List<List<int[]>> graph = new ArrayList<>();

            // Read cities and build the graph
            for (int i = 0; i < n; i++) {
                String cityName = readCityName(sc);
                cityIndex.put(cityName, i);
                int p = readIntWithValidation(sc, "Enter number of neighbors for city " + cityName + ": ", 0, n - 1);
                List<int[]> neighbors = new ArrayList<>();
                for (int j = 0; j < p; j++) {
                    int neighborIndex = readIntWithValidation(sc, "Enter neighbor index (1 to " + n + "): ", 1, n) - 1;
                    int cost = readIntWithValidation(sc, "Enter cost for connection: ", 1, Integer.MAX_VALUE);
                    neighbors.add(new int[]{neighborIndex, cost});
                }
                graph.add(neighbors);
            }

            // Read paths to find
            int r = readIntWithValidation(sc, "Enter number of paths to find: ", 1, 100);
            sc.nextLine(); // Consume the rest of the line
            for (int i = 0; i < r; i++) {
                String[] path = sc.nextLine().split(" ");
                if (path.length != 2) {
                    System.out.println("Invalid path input. Expected two city names.");
                    continue;
                }
                String sourceCity = path[0];
                String destinationCity = path[1];
                if (!cityIndex.containsKey(sourceCity) || !cityIndex.containsKey(destinationCity)) {
                    System.out.println("Invalid city names.");
                    continue;
                }
                int sourceIndex = cityIndex.get(sourceCity);
                int destinationIndex = cityIndex.get(destinationCity);

                // Calculate minimum cost using Dijkstra
                int cost = dijkstra(graph, sourceIndex, destinationIndex, n);
                System.out.println(cost);
            }

            if (testCases > 0) {
                sc.nextLine(); // Consume the empty line between test cases
            }
        }
        sc.close();
    }

    // Method to validate and read integers within a specific range
    private static int readIntWithValidation(Scanner sc, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Value out of range. Please enter a value between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // Consume invalid input
            }
        }
        return value;
    }

    // Method to read and validate a city name (only lowercase letters and up to 10 characters)
    private static String readCityName(Scanner sc) {
        String cityName;
        while (true) {
            System.out.print("Enter city name (lowercase, max 10 characters): ");
            cityName = sc.next();
            if (cityName.matches("[a-z]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid city name. Please enter a valid city name (lowercase letters, up to 10 characters).");
            }
        }
        return cityName;
    }

    // Dijkstra's algorithm for finding the minimum cost between two cities
    private static int dijkstra(List<List<int[]>> graph, int start, int end, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDist = current[1];

            if (currentNode == end) {
                return currentDist;
            }

            if (currentDist > distances[currentNode]) {
                continue;
            }

            for (int[] neighbor : graph.get(currentNode)) {
                int neighborNode = neighbor[0];
                int edgeCost = neighbor[1];
                int newDist = currentDist + edgeCost;

                if (newDist < distances[neighborNode]) {
                    distances[neighborNode] = newDist;
                    pq.add(new int[]{neighborNode, newDist});
                }
            }
        }
        return -1;
    }
}