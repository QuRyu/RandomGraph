package generator;

import org.random.api.RandomOrgClient;

import java.io.IOException;

public class RandomOrgGenerator extends NumberGenerator {
    private RandomOrgClient generator;

    private int graphSize;

    private static final int API_REQUEST_SIZE_UPPER_LIMIT = 10000;
    private static final int API_REQUEST_MAX = 1000000000;

    public RandomOrgGenerator(String apiKey) {
        generator = RandomOrgClient.getRandomOrgClient(apiKey);
    }


    protected int generateGraphSize() throws IOException {
        return generator.generateIntegers(1, GRAPH_SIZE_LOW, GRAPH_SIZE_HIGH)[0];
    }

    protected int generateCacheSize() throws IOException {
        return generator.generateIntegers(1, 1, API_REQUEST_MAX)[0] * 2;
    }

    protected int getRandomsUpperLimit() throws IOException {
        return API_REQUEST_SIZE_UPPER_LIMIT;
    }

    /**
     * Request more random numbers from API
     * @param n the number of random numbers to request
     * @throws IOException
     */
    @Override
    protected void refillCache(int n) throws IOException {
        randoms = generator.generateIntegers(n, 1, API_REQUEST_MAX);
    }

    protected void initialize_generator() throws IOException {
        return;
    }

}
