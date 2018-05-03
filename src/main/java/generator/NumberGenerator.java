package generator;

import java.io.IOException;

public abstract class NumberGenerator {
    protected static final int GRAPH_SIZE_HIGH = 10000;
    protected static final int GRAPH_SIZE_LOW = 1;

    protected int randoms[];
    protected int graphSize;

    private int cacheSize;
    private int cacheIndex;


    /**
     * Initialize internal variables.
     * Must be called each time either after the object is initialized
     * or getRandomNumber() has returned -1.
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
     * This method will try to request more random numbers if it turns out that
     * it has not generated enough number of random numbers (the total number of random
     * numbers that need be generated is also itself a random number), by calling
     * `refillCache(int)`.
     *
     * If -1 is returned, this indicates that enough random numbers have been
     * generated and the loop to extract random numbers should stop.
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

    public int getTotalNumberOfRandomNumbers() {
        return cacheSize;
    }

    protected abstract int generateGraphSize() throws IOException;
    protected abstract int generateCacheSize() throws IOException;
    protected abstract int getRandomsUpperLimit() throws IOException;

    protected abstract void refillCache(int n) throws IOException;

    protected abstract void initialize_generator() throws IOException;
}
