package generator;

import java.io.IOException;
import java.util.Random;


/**
 * The implementation of random number generator, using the `Random`
 * provided by java util library.
 */
public class UtilGenerator extends NumberGenerator {
    private Random generator; // provided by Java.Util

    private static final int RANDOMS_SIZE_UPPER_LIMIT = 10000; // the cache size should be no larger than this number

    protected int generateGraphSize() throws IOException {
        int i = generator.nextInt(GRAPH_SIZE_HIGH);
        if (i == 0) return generateGraphSize();
        else return  i;
    }

    protected int generateCacheSize() throws IOException {
        return generator.nextInt(10000);
    }

    protected int getRandomsUpperLimit() throws IOException {
        return RANDOMS_SIZE_UPPER_LIMIT;
    }

    protected void refillCache(int n) throws IOException {
        randoms = new int[n];
        for (int i = 0; i < n; i++) {
            randoms[i] = generator.nextInt(graphSize);
        }
    }

    /**
     * Uses current time as the seed.
     * @throws IOException
     */
    protected void initialize_generator() throws IOException {
        generator = new Random(System.currentTimeMillis());
    }
}
