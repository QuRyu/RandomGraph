import graph.GraphKind;

import java.util.ArrayList;
import java.util.List;

class ThreadManager {
    private static final int TOTAL_RUNNING_THREADS = 4;
    private static final int ROUNDS_PER_THREAD = 100;

    private int totalRounds;
    private int numOfGraphs; // number of graphs generated per round
    private List<Double> undirectedResults;
    private List<Double> directedResults;

    private List<Runnable> threads;

    ThreadManager(int totalRounds, int numOfGraph) {
        undirectedResults = new ArrayList<Double>();
        directedResults = new ArrayList<Double>();
        this.totalRounds = totalRounds;
        this.numOfGraphs = numOfGraph;

        threads = new ArrayList<Runnable>();
        for (int i = 0; i < TOTAL_RUNNING_THREADS/2; i++) {
            Runnable r = new StatsThread(ThreadManager.this,
                    GraphKind.Directed, numOfGraphs, ROUNDS_PER_THREAD);
            threads.add(r);
            r.run();
        }
        for (int i = 0; i < TOTAL_RUNNING_THREADS/2; i++) {
            Runnable r = new StatsThread(ThreadManager.this,
                    GraphKind.Unditected, numOfGraphs, ROUNDS_PER_THREAD);
            threads.add(r);
            r.run();
        }
    }

    void finished(double result, GraphKind kind) {
        switch (kind) {
            case Directed:
                directedResults.add(result);
                if (directedResults.size() == totalRounds/ROUNDS_PER_THREAD)
                    printResult(GraphKind.Directed);
                else  {
                    Runnable r = new StatsThread(ThreadManager.this,
                            GraphKind.Directed, numOfGraphs, ROUNDS_PER_THREAD);
                    threads.add(r);
                    r.run();
                }
                break;
            case Unditected:
                undirectedResults.add(result);
                if (undirectedResults.size() == totalRounds/ROUNDS_PER_THREAD)
                    printResult(GraphKind.Unditected);
                else {
                    Runnable r = new StatsThread(this,
                            GraphKind.Unditected, numOfGraphs, ROUNDS_PER_THREAD);
                    threads.add(r);
                    r.run();
                }
                break;
        }
    }

    private void printResult(GraphKind kind) {
        double total = 0;


        System.out.print("After " + totalRounds +
                " rounds, each of which consists of generating 10000 graphs" +
                ", the probability for ");

        switch (kind) {
            case Unditected:
                for (Double d :
                        undirectedResults) {
                    total += d;
                }
                total = total / undirectedResults.size();
                System.out.print("undirected graphs");
                break;
            case Directed:
                for (Double d :
                        directedResults) {
                    total += d;
                }
                total = total / directedResults.size();
                System.out.print("directed graphs");
                break;
        }

        System.out.print(" is " + total + "%");
    }

}