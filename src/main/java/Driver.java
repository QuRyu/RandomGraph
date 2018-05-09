public class Driver {

    private static final int GRAPHS_PER_ROUND = 10000;
    private static final int ROUNDS = 1000;

    public static void main(String[] args) {
        ThreadManager manager = new ThreadManager(ROUNDS, GRAPHS_PER_ROUND);

    }
}
