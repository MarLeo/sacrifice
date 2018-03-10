package helloWorld;

public class RealHelloWorldMultithreaded {
    public static void main(String[] args) {
        String countries[] = {"France", "India", "China", "USA", "Germany"};

        for (String country : countries) {
            new Greeter(country).start();
        }
    }

    public static class Greeter extends Thread {
        private String country;

        public Greeter(String country) {
            super(country + " thread"); // call the thread constructor to name the thread
            this.country = country;
        }

        public void run() {
            System.out.println("Hello " + country + "!");
        }
    }
}
