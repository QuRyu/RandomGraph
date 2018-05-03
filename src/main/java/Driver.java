import generator.NumberGenerator;
import generator.UtilGenerator;

import java.io.IOException;

public class Driver {

    private static final String API_KEY = "7ddd3ef7-bda7-424f-b093-9fe9d453de4a";

    public static void main(String[] args) {
        NumberGenerator generator = new UtilGenerator();

        try {
            generator.initialize();
            System.out.println(generator.getGraphSize());
            System.out.println(generator.getTotalNumberOfRandomNumbers());
            //int i;

            //while ((i = generator.getRandomNumber()) > -1) {
            //    System.out.println(i);
            //}

        } catch (IOException e) {
            System.out.println("failed to initialize");
        }


    }
}
