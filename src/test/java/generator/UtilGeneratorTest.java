package generator;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class UtilGeneratorTest {

    NumberGenerator generator;

    @Before
    public void setup() throws IOException {
        generator = new UtilGenerator();
        generator.initialize();
    }

    @Test
    public void test() throws IOException {
        System.out.println("Total number of random numbers to be generated: " + generator.getTotalNumberOfRandomNumbers());
        for (int i = 0; i < generator.getTotalNumberOfRandomNumbers(); i++) {
            System.out.print(generator.getRandomNumber() + " ");
            // TODO: 5/7/18 why is it zero?

        }
    }
}
