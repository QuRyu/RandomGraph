package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class UndirectedGraphTest {

    List<UndirectedGraph> conGraphs; // list of strongly connected graphs
    List<UndirectedGraph> disconGraphs; // list of (possibly partially connected) graphs

    @Before
    public void setup() {
        conGraphs = new ArrayList<UndirectedGraph>(4);
        disconGraphs = new ArrayList<UndirectedGraph>(5);

        // empty graph should trivially be connected
        UndirectedGraph graph1 = new UndirectedGraph(0);
        conGraphs.add(graph1);

        // connected graph
        UndirectedGraph graph2 = new UndirectedGraph(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 4);
        conGraphs.add(graph2);

        UndirectedGraph graph3 = new UndirectedGraph(6);
        graph3.addEdge(0, 5);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 3);
        graph3.addEdge(1, 2);
        graph3.addEdge(2, 4);
        conGraphs.add(graph3);

        UndirectedGraph graph4 = new UndirectedGraph(5);
        graph4.addEdge(0, 1);
        graph4.addEdge(0, 2);
        graph4.addEdge(0, 3);
        graph4.addEdge(0, 4);
        conGraphs.add(graph4);

        // not strongly connected graphs
        UndirectedGraph graph5 = new UndirectedGraph(5);
        disconGraphs.add(graph5); // isolate nodes

        UndirectedGraph graph6 = new UndirectedGraph(5);
        graph6.addEdge(0, 1);
        graph6.addEdge(0, 2);
        graph6.addEdge(0, 3);
        // graph6.addEdge(0, 4); one node is not connected
        disconGraphs.add(graph6);
    }

    @Test
    public void conntectedTest() {
        for (UndirectedGraph graph:
                conGraphs) {
            assertTrue(graph.connected());
        }

        for (UndirectedGraph graph :
                disconGraphs) {
            assertFalse(graph.connected());
        }
    }
}
