package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DirectedGraphTest {

    List<DirectedGraph> conGraphs;
    List<DirectedGraph> disconGraphs;

    @Before
    public void setup() {
        conGraphs = new ArrayList<DirectedGraph>();
        disconGraphs = new ArrayList<DirectedGraph>();

        // empty graph, should be trivially connected
        DirectedGraph graph0 = new DirectedGraph(0);
        conGraphs.add(graph0);

        // connected graphs
        DirectedGraph graph1 = new DirectedGraph(5);
        graph1.addEdge(0,1);
        graph1.addEdge(1,2);
        graph1.addEdge(2,3);
        graph1.addEdge(3,4);
        graph1.addEdge(4,0);
        conGraphs.add(graph1);

        DirectedGraph graph8 = new DirectedGraph(5);
        graph8.addEdge(0,1);
        graph8.addEdge(0,3);
        graph8.addEdge(1,2);
        graph8.addEdge(2,0);
        graph8.addEdge(3,4);
        graph8.addEdge(4,0);
        conGraphs.add(graph8);

        // not strongly connected graphs
        DirectedGraph graph2 = new DirectedGraph(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 4);
        disconGraphs.add(graph2);

        DirectedGraph graph3 = new DirectedGraph(6);
        graph3.addEdge(0, 5);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 3);
        graph3.addEdge(1, 2);
        graph3.addEdge(2, 4);
        disconGraphs.add(graph3);

        DirectedGraph graph4 = new DirectedGraph(5);
        graph4.addEdge(0, 1);
        graph4.addEdge(0, 2);
        graph4.addEdge(0, 3);
        graph4.addEdge(0, 4);
        disconGraphs.add(graph4);

        DirectedGraph graph5 = new DirectedGraph(5);
        disconGraphs.add(graph5); // isolate nodes

        DirectedGraph graph6 = new DirectedGraph(5);
        graph6.addEdge(0, 1);
        graph6.addEdge(0, 2);
        graph6.addEdge(0, 3);
        disconGraphs.add(graph6);

        DirectedGraph graph7 = new DirectedGraph(6);
        graph7.addEdge(0, 1);
        graph7.addEdge(1, 2);
        graph7.addEdge(2, 0);
        graph7.addEdge(2, 3);
        graph7.addEdge(3, 4);
        graph7.addEdge(4, 5);
        disconGraphs.add(graph7);
    }

    
    @Test
    public void connectedTest() {
        for (Graph g :
                conGraphs) {
            if (!g.connected())
                g.printGraph();
            assertTrue(g.connected());
        }

        for (Graph g :
                disconGraphs) {
            g.printGraph();
            System.out.println();
            System.out.println();
            assertFalse(g.connected());
        }
        
    }
}
