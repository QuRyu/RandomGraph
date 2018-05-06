package graph;

import java.util.Stack;

public class DirectedGraph extends Graph {

    public DirectedGraph(int v) {
        super(v);
    }

    public void addEdge(int src, int dst) {
        if (!adjArr[src].contains(dst))
            adjArr[src].add(dst);
    }

    public boolean connected() {
        Variables var = new Variables(this.v);
        findSccs(var);

        return var.sccCount == 1;
    }

    /**
     * Find strongly connected components of the graph
     * @param var
     */
    private void findSccs(Variables var) {

        for (int i = 0; i < v; i++) {
            if (var.isUnvisited(i))
                dfs(i, var);
        }
    }

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

    private class Variables {
        private static final int unvisited = -1;
        private int idCounter;

        int sccCount;

        int[] ids;
        int[] low;
        boolean[] onStack;

        Stack<Integer> stack;

        Variables(int V) {
            idCounter = 0;
            sccCount = 0;

            ids = new int[V];
            low = new int[V];
            onStack = new boolean[V];

            stack = new Stack<Integer>();

            for (int i = 0; i < V; i++) {
                ids[i] = unvisited;
                onStack[i] = false;
            }
        }

        void assignId(int vertex) {
            low[vertex] = idCounter;
            ids[vertex] = idCounter++;
        }

        void popStack(int vertex) {
            int i;
            for (i = stack.pop(); ; i = stack.pop()) {
                onStack[i] = false;
                low[i] = ids[vertex];
                if (i == vertex) break;
            }
            sccCount++;
        }

        boolean isUnvisited(int vertex) {
            return ids[vertex] == unvisited;
        }
    }
}
