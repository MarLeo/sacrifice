import threads.Greater;

import java.util.concurrent.TimeUnit;

public class RealHelloWorldSingleThreaded {

    public static void main(String[] args) throws InterruptedException {


        String countries[] = {"France", "India", "China", "USA", "Germany"};

        for (String country : countries) {
            Greater greater = new Greater(country);
            new Thread(greater, country + " thread").start();
            // System.out.println("Hello " + country +"!");
        }

        /*Arrays.stream(countries)
                .forEach(country -> System.out.println("Hello " + country +"!"));*/

        int val = 3;

        System.out.println("Sleeping for " + val + "us");
        TimeUnit.MICROSECONDS.sleep(val);

        System.out.println("Sleeping for " + val + "ms");
        TimeUnit.MILLISECONDS.sleep(val);

        System.out.println("Sleeping for " + val + "s");
        TimeUnit.SECONDS.sleep(val);


    }

}
