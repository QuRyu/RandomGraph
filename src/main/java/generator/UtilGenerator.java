package generator;

import java.io.IOException;
import java.util.Random;

public class UtilGenerator extends NumberGenerator {
    private Random generator;

    private static final int RANDOMS_SIZE_UPPER_LIMIT = 10000;

    protected int generateGraphSize() throws IOException {
        return generator.nextInt(GRAPH_SIZE_HIGH);
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

    protected void initialize_generator() throws IOException {
        generator = new Random(System.currentTimeMillis());
    }
}
