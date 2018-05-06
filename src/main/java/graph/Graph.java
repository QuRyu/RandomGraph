package graph;

import java.util.LinkedList;

public abstract class Graph {

    protected int v;
    protected LinkedList<Integer>[] adjArr;

    public Graph(int v) {
        this.v = v;
        adjArr = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjArr[i] = new LinkedList<Integer>();
        }
    }

    public void printGraph() {
        for (int i = 0; i < adjArr.length; i++) {
            System.out.println("Adjacency list of vertex" + i);
            System.out.print("head ");
            for (Integer index :
                    adjArr[i]) {
                System.out.print("-> " + index);
            }
        }
    }

    public abstract void addEdge(int src, int dst);

    public abstract boolean connected();

}
