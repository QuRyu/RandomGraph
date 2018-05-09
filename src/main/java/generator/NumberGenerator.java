package generator;

import java.io.IOException;

/**
 * The interface for random number generation, with each subclass implementing a specific random number generation strategy.
 * This interface is specifically tailored to produce random graphs.
 *
 * Each subclass needs to implement several methods that return random numbers.
 */
// TODO: 5/9/18 Find probability of different sizes of graph and number of edges
public abstract class NumberGenerator {
    protected static final int GRAPH_SIZE_HIGH = 1000; // the upper limit of the graph size
    protected static final int GRAPH_SIZE_LOW = 1; // the lower limit of the graph size

    protected int randoms[]; // the cache for random numbers
    protected int graphSize; // how large the graph is

    private int cacheSize; // upper limit of the cache size
    private int cacheIndex; // which element the cache is currently pointing to


    /**
     * Initialize internal variables.
     * Must be called either after the object is constructed or
     * after `getRandomNumber` has returned -1.
     */
    public void initialize() throws IOException {
        initialize_generator();

        graphSize = generateGraphSize();
        cacheSize = generateCacheSize();

        randoms = new int[1];
        cacheIndex = 2;
    }

    /**
     * Returns a random number.
     *
     * If -1 is returned, this indicates that enough random numbers have been
     * generated and the loop to extract random numbers from the generator
     * should stop.
     *
     * @return a random number
     */
    public int getRandomNumber() throws IOException {
        if (cacheIndex >= randoms.length) {
            if (cacheSize > getRandomsUpperLimit()) {
                refillCache(getRandomsUpperLimit());
                cacheSize -= getRandomsUpperLimit();
            } else if (cacheSize > 0) {
                refillCache(cacheSize);
                cacheSize = 0;
            } else
                return -1;

            cacheIndex = 0;
        }
        return randoms[cacheIndex++];
    }

    /**
     *
     * @return the randomly initialized graph size
     */
    public int getGraphSize() {
        return graphSize;
    }

    /**
     * This number divided by 2 is equal to the number of randomly generated edges.
     *
     * @return The total number of random numbers to be generated.
     */
    public int getTotalNumberOfRandomNumbers() {
        return cacheSize;
    }

    /**
     *
     * @return a random number indicating the size of the graph
     * @throws IOException
     */
    protected abstract int generateGraphSize() throws IOException;

    /**
     *
     * @return a random number indicating the number of edges
     * @throws IOException
     */
    protected abstract int generateCacheSize() throws IOException;

    /**
     * Because some implementations of the random number generator may limit
     * the number of random numbers that can generated at a time, it is important
     * to limit the cache size to both suit the implementation detail and save space
     * (the `cacheSize` might be tool large to fit into memory).
     *
     * @return how large the cache size should be
     * @throws IOException
     */
    protected abstract int getRandomsUpperLimit() throws IOException;

    /**
     * If not enough random numbers have been consumed while cache is now empty,
     * generate new random numbers and fill them in.
     *
     * Note that the invariant is that the parameter `n` should be equal to or
     * smaller than `cacheSize`.
     *
     * @param n the number of random numbers to be generated and filled in
     * @throws IOException
     */
    protected abstract void refillCache(int n) throws IOException;

    /**
     * Let each implementation finish any bookkeeping that must be done.
     *
     * @throws IOException
     */
    protected abstract void initialize_generator() throws IOException;
}
