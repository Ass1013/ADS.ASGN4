import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Task (3 or 5): ");
        int task = sc.nextInt();

        switch (task) {
            case 3:
                runTask3();
                break;
            case 5:
                runTask5();
                break;
        }
    }

    static void runTask3() {
        Map<String, List<String>> adj = new LinkedHashMap<>();
        adj.put("A", Arrays.asList("C", "B", "D"));
        adj.put("B", Arrays.asList("A", "C", "E", "G"));
        adj.put("C", Arrays.asList("A", "B", "D"));
        adj.put("D", Arrays.asList("C", "A"));
        adj.put("E", Arrays.asList("G", "F", "B"));
        adj.put("F", Arrays.asList("G", "E"));
        adj.put("G", Arrays.asList("F", "B"));

        List<String> dfsResult = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs("A", adj, visited, dfsResult);
        System.out.println("DFS Order: " + String.join(" ", dfsResult));

        List<String> bfsResult = new ArrayList<>();
        visited.clear();
        bfs("A", adj, visited, bfsResult);
        System.out.println("BFS Order: " + String.join(" ", bfsResult));
    }

    static void dfs(String u, Map<String, List<String>> adj, Set<String> visited, List<String> res) {
        visited.add(u);
        res.add(u);
        for (String v : adj.getOrDefault(u, Collections.emptyList())) {
            if (!visited.contains(v)) {
                dfs(v, adj, visited, res);
            }
        }
    }

    static void bfs(String start, Map<String, List<String>> adj, Set<String> visited, List<String> res) {
        Queue<String> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        while (!q.isEmpty()) {
            String u = q.poll();
            res.add(u);
            for (String v : adj.getOrDefault(u, Collections.emptyList())) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    q.add(v);
                }
            }
        }
    }

    static class Edge {
        String to;
        int weight;
        Edge(String t, int w) {
            to = t;
            weight = w;
        }
    }

    static class Node implements Comparable<Node> {
        String name;
        int dist;
        Node(String n, int d) {
            name = n;
            dist = d;
        }
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static void runTask5() {
        Map<String, List<Edge>> adj = new HashMap<>();
        adj.put("Edinburgh", Arrays.asList(new Edge("Glasgow", 70), new Edge("Stirling", 50), new Edge("Perth", 100))); //
        adj.put("Glasgow", Arrays.asList(new Edge("Edinburgh", 70), new Edge("Stirling", 50))); //
        adj.put("Stirling", Arrays.asList(new Edge("Edinburgh", 50), new Edge("Glasgow", 50), new Edge("Perth", 40))); //
        adj.put("Perth", Arrays.asList(new Edge("Edinburgh", 100), new Edge("Stirling", 40), new Edge("Dundee", 60))); //
        adj.put("Dundee", Arrays.asList(new Edge("Perth", 60))); //

        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (String city : adj.keySet()) {
            dist.put(city, Integer.MAX_VALUE);
        }

        dist.put("Edinburgh", 0);
        pq.add(new Node("Edinburgh", 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.dist > dist.get(curr.name)) continue;

            for (Edge e : adj.getOrDefault(curr.name, Collections.emptyList())) {
                int newDist = curr.dist + e.weight;
                if (newDist < dist.get(e.to)) {
                    dist.put(e.to, newDist);
                    prev.put(e.to, curr.name);
                    pq.add(new Node(e.to, newDist));
                }
            }
        }

        List<String> path = new ArrayList<>();
        String curr = "Dundee";
        while (curr != null) {
            path.add(curr);
            curr = prev.get(curr);
        }
        Collections.reverse(path);

        System.out.println("Shortest Path: " + String.join(" -> ", path));
        System.out.println("Total Distance: " + dist.get("Dundee"));
    }
}