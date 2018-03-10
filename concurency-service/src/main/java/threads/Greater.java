package threads;

public class Greater /*extends Thread*/ implements Runnable {

    private String country;

    public Greater(String country) {
        this.country = country;
    }


    public void run() {
        long id = Thread.currentThread().getId();
        System.out.println(id + ":" + "Hello " + country + "!");
    }

}
