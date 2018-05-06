package graph;

import generator.NumberGenerator;

import java.io.IOException;

public class GraphGenerator {
    private NumberGenerator generator;

    public GraphGenerator(NumberGenerator generator) {
        this.generator = generator;
    }

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

        for (int k = 0; k < generator.getTotalNumberOfRandomNumbers(); k++) {
            i = generator.getRandomNumber();
            j = generator.getRandomNumber();
            g.addEdge(i, j);
        }

        return g;
    }

}
