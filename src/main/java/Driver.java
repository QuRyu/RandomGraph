import generator.NumberGenerator;
import generator.UtilGenerator;
import graph.Graph;
import graph.GraphGenerator;
import graph.GraphKind;

import java.io.IOException;

public class Driver {

    private static final int ROUNDS = 1000;

    public static void main(String[] args) throws IOException {
        int[][] sizes = {{1000, 1000}, {1000, 2000}, {1000, 5000}, {1000, 10000}};
        analyse(sizes);
    }

    /**
     * The method for generating random graphs and collecting the data
     * @param sizes different combination of graph size and edge size
     * @throws IOException
     */
    private static void analyse(int[][] sizes) throws IOException {
        for (int i = 0; i < sizes.length; i++) { // 0 is the graph size, 1 is the edges size
            NumberGenerator nGenerator = new UtilGenerator(sizes[i][0], sizes[i][1]);
            GraphGenerator gGenerator = new GraphGenerator(nGenerator);
            boolean[] connected = new boolean[ROUNDS];
            double[] stats = new double[ROUNDS];

            for (int j = 0; j < ROUNDS; j++) {
                mkGraphs(gGenerator, connected, GraphKind.Unditected);
                stats[j] = collect(connected);
            }
            System.out.println("For undirected graphs, the probability of connectivity, given a random graph size between 0 and " +
                    sizes[i][0] + " and a random edge size between 0 and " + sizes[i][1] + " is " + collect(stats));

            for (int j = 0; j < ROUNDS; j++) {
                mkGraphs(gGenerator, connected, GraphKind.Directed);
                stats[j] = collect(connected);
            }
            System.out.println("For directed graphs, the probability of connectivity, given a random graph size between 0 and " +
                    sizes[i][0] + " and a random edge size between 0 and " + sizes[i][1] + " is " + collect(stats) + "%");

            System.out.println();
            System.out.println();
        }

    }

    /**
     * Producing random graphs
     * @param gGenerator the random graph generator
     * @param connected data structure to store the connectivity of each graph
     * @param kind the graph kind
     * @throws IOException
     */
    private static void mkGraphs(GraphGenerator gGenerator,
                          boolean[] connected, GraphKind kind) throws IOException {
        Graph g;

        for (int i = 0; i < connected.length; i++) {
            g = gGenerator.generateGraph(kind);
            connected[i] = g.connected();
        }
    }

    /**
     * A fold-like method to calculate the percentage of connectivity
     * @param connected data structure to store the connectivity of each graph
     * @return the percentage of connectivity
     */
    private static double collect(boolean[] connected) {
        int numConnected = 0;

        for (int i = 0; i < connected.length; i++) {
            numConnected = connected[i] ? ++numConnected : numConnected;
        }

        return (numConnected / 1.0) / connected.length * 100;
    }

    /**
     * A fold-like method to calculate the probability of connectivity
     * @param stats data structure to store percentage of connectivity of graphs generated in each round
     * @return the probability of connectivity
     */
    private static double collect(double[] stats) {
        double total = 0.0;

        for (int i = 0; i < stats.length ; i++) {
            total += stats[i];
        }

        return total / stats.length;
    }
}
