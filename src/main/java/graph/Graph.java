package graph;

import java.util.LinkedList;

/**
 * The interface for graph data structure.
 */
public abstract class Graph {

    protected int v; // number of vertices in the graph
    protected LinkedList<Integer>[] adjArr; // use adjacency matrix to represent the graph

    /**
     *
     * @param v the size of the graph
     */
    Graph(int v) {
        this.v = v;
        adjArr = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjArr[i] = new LinkedList<Integer>();
        }
    }

    /**
     * Print the graph
     */
    public void printGraph() {
        for (int i = 0; i < adjArr.length; i++) {
            System.out.println("Adjacency list of vertex " + i);
            System.out.print("head ");
            for (Integer index :
                    adjArr[i]) {
                System.out.print("-> " + index);
            }
            System.out.println();
        }
    }

    /**
     * Add edges between two vertices `src` and `dst`. Dependends on the kind of graph
     * directed or undirected edge may be formed between them.
     * @param src the starting vertice
     * @param dst the terminal vertice
     */
    public abstract void addEdge(int src, int dst);

    /**
     * Test if the graph is connected.
     * @return true if the graph is connected; otherwise false
     */
    public abstract boolean connected();

}
