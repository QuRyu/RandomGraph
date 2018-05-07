package graph;

import java.util.Stack;

public class DirectedGraph extends Graph {

    // not public so that only GraphGenerator could produce them
    DirectedGraph(int v) {
        super(v);
    }

    /**
     * Add a directed edge between two vertices
     *
     * @param src the starting vertice
     * @param dst the terminal vertice
     */
    public void addEdge(int src, int dst) {
        if (!adjArr[src].contains(dst))
            adjArr[src].add(dst);
    }

    /**
     * Uses Tarjan's algorithm to test if the graph is connected.
     *
     * @return true if the graph is connected; otherwise false.
     */
    public boolean connected() {
        if (v == 0) // an empty graph is trivially connected
            return true;

        Variables var = new Variables(this.v);
        findSccs(var);

        return var.sccCount == 1; // a graph is connected iff the whole graph is a strongly connected component
    }

    /**
     * Find strongly connected components of the graph.
     *
     * Part of implementation of the Tarjan's algorithm.
     * @param var the object to store bookkeeping variables
     */
    private void findSccs(Variables var) {
        for (int i = 0; i < v; i++) {
            if (var.isUnvisited(i))
                dfs(i, var);
        }
    }

    /**
     * Depth-firth search with augmented functionality to push and pop stacks
     * @param vertex the current vertex on which the search is implementing
     * @param var the object to store bookkeeping variables
     */
    private void dfs(int vertex, Variables var) {
        var.stack.push(vertex);
        var.onStack[vertex] = true;
        var.assignId(vertex);

        for (Integer neightbor :
                adjArr[vertex]) {
            if (var.isUnvisited(neightbor))
                dfs(neightbor, var);
            if (var.onStack[neightbor])
                var.low[vertex] = Math.min(var.low[vertex], var.low[neightbor]);
        }

        if (var.ids[vertex] == var.low[vertex])
            var.popStack(vertex);
    }

    /**
     * The container to store each variable
     */
    private class Variables {
        private static final int UNVISITED = -1; // label all vertices that haven't been visited as UNVISITED
        private int idCounter; // a counter to assign each vertex a fresh id

        int sccCount; // the total number of strongly connected components in a graph

        int[] ids; // the id of vertices, initialized to UNVISITED
        int[] low; // the lowest id in a strongly component
        boolean[] onStack; // if vertices on the stacck

        Stack<Integer> stack; // the stack

        /**
         * Initialize each variable
         *
         * @param V the size of the graph
         */
        Variables(int V) {
            idCounter = 0;
            sccCount = 0;

            ids = new int[V];
            low = new int[V];
            onStack = new boolean[V];

            stack = new Stack<Integer>();

            for (int i = 0; i < V; i++) {
                ids[i] = UNVISITED;
                onStack[i] = false;
            }
        }

        /**
         * Assign a fresh id to a vertex
         *
         * @param vertex the vertex to which a new id is assigned
         */
        void assignId(int vertex) {
            low[vertex] = idCounter;
            ids[vertex] = idCounter++;
        }

        /**
         * Pops all variables on the stack before the vertex given
         * @param vertex
         */
        void popStack(int vertex) {
            int i;
            for (i = stack.pop(); ; i = stack.pop()) {
                onStack[i] = false;
                low[i] = ids[vertex];
                if (i == vertex) break;
            }
            sccCount++;
        }

        /**
         * Test if the vertex is on the stack.
         * @param vertex the vertex to be tested
         * @return true if the vertex is on the stack; otherwise false.
         */
        boolean isUnvisited(int vertex) {
            return ids[vertex] == UNVISITED;
        }
    }
}
