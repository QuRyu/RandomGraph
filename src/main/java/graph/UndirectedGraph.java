package graph;

import java.util.LinkedList;

public class UndirectedGraph extends Graph {

    UndirectedGraph(int v) {
        super(v);
    }

    /**
     * Add a undirected edge between two vertices
     * @param src the starting vertice
     * @param dst the terminal vertice
     */
    public void addEdge(int src, int dst) {
        if (!adjArr[src].contains(dst)) {
            adjArr[src].add(dst);
            adjArr[dst].add(src);
        }
    }

    /**
     * Implements breadth-first search to test if the graph is connected.
     * @return true if the graph is connected; otherwise false.
     */
    public boolean connected() {
        if (v == 0) // an empty graph is trivially connected
            return true;

        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[v];

        visited[0] = true;
        for (int i = 1; i < v; i++)
            visited[i] = false;

        queue.add(0);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (Integer adj : adjArr[node]) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                }
            }
        }


        boolean result = true;
        for (int i = 0; i < v; i++) {
            result = result && visited[i];
        }
        return result;
    }

}
