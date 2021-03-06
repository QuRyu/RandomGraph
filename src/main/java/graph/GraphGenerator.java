package graph;

import generator.NumberGenerator;

import java.io.IOException;

/**
 * A generator for graphs based on the number generator.
 */
public class GraphGenerator {
    private NumberGenerator generator;

    /**
     * The constructor.
     * @param generator number generator
     */
    public GraphGenerator(NumberGenerator generator) {
        this.generator = generator;
    }

    /**
     * generate a random graph
     * @param kind the graph kind
     * @return a random graph of the specified kind
     * @throws IOException
     */
    public Graph generateGraph(GraphKind kind) throws IOException {
        generator.initialize();
        int i, j;
        Graph g;

        switch (kind) {
            case Directed:
                g = new DirectedGraph(generator.getGraphSize());
                break;
            case Unditected:
                g = new UndirectedGraph(generator.getGraphSize());
                break;
            default:
                throw new RuntimeException();

        }

        int n = generator.getTotalNumberOfRandomNumbers()/2;
        for (int k = 0; k < n; k++) {
            i = generator.getRandomNumber();
            j = generator.getRandomNumber();
            g.addEdge(i, j);
        }

        return g;
    }

}
