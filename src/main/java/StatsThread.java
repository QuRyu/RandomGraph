import generator.NumberGenerator;
import generator.UtilGenerator;
import graph.Graph;
import graph.GraphGenerator;
import graph.GraphKind;

import java.io.IOException;

class StatsThread implements Runnable {
    private ThreadManager callback;
    private GraphKind kind;
    private int numGraphs; // the number of graphs generated per round
    private int round; // the number of rounds run by one thread

    StatsThread(ThreadManager callback, GraphKind kind, int numGraphs, int round) {
        this.callback = callback;
        this.numGraphs = numGraphs;
        this.round =  round;
        this.kind = kind;
    }

    public void run() {
        NumberGenerator nGenerator = new UtilGenerator();
        GraphGenerator gGenerator = new GraphGenerator(nGenerator);

        boolean[] connected = new boolean[numGraphs];
        double[] stats = new double[round];
        double result;

        for (int j = 0; j < round; j++) {
                try {
                    mkGraphs(gGenerator, connected, kind);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Unknown runtime exception");
                }
            stats[j] = collect(connected, kind);
        }

        result = collect(stats);
        System.out.println(result);
        callback.finished(result, kind);
    }

    private void mkGraphs(GraphGenerator gGenerator,
                          boolean[] connected, GraphKind kind) throws IOException {
        Graph g;

        for (int i = 0; i < connected.length; i++) {
            g = gGenerator.generateGraph(kind);
            connected[i] = g.connected();
        }
    }

    private double collect(boolean[] connected, GraphKind kind) {
        int numConnected = 0;

        for (int i = 0; i < connected.length; i++) {
            numConnected = connected[i] ? ++numConnected : numConnected;
        }

        return (numConnected / 1.0) / connected.length * 100;
    }

    private double collect(double[] stats) {
        double total = 0.0;

        for (int i = 0; i < stats.length ; i++) {
            total += stats[i];
        }

        return total / stats.length;
    }
}