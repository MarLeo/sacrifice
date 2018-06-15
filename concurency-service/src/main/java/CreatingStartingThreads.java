import lombok.Data;

public class CreatingStartingThreads {


    public static void main(String[] args) {

        Counter counter = new Counter();

        /*Thread thread = new MyThread(counter);
        Thread runnable = new Thread(new MyRunnable(), "New Runnable");

        thread.start();
        runnable.start();*/

        Thread threadA = new CounterThread(counter);
        Thread threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();

        System.out.println("Result: " + counter.getCount());


        Counter counterA = new Counter();
        Counter counterB = new Counter();
        Thread thread1 = new CounterThread(counterA);
        Thread thread2 = new CounterThread(counterB);

        thread1.start();
        thread2.start();

        System.out.println("Counter A: " + counterA.getCount() + " Counter B: " + counterB.getCount());

    }


    public static class MyThread extends Thread {

        protected Counter counter = null;

        private MyThread() {
            super("New Thread");
        }

        public MyThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            System.out.println("Hello World Thread");
            Thread thread = Thread.currentThread();
            System.out.println("Thread Name: " + thread.getName());
            this.counter.add(2);
            System.out.println("Counter: " + counter.getCount());
        }
    }


    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello World Runnable");
            Thread thread = Thread.currentThread();
            System.out.println("Thread Name: " + thread.getName());
        }
    }


    public static class CounterThread extends Thread {
        protected Counter counter = null;

        public CounterThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                counter.add(i);
            }
        }
    }


    @Data
    public static class Counter {

        protected long count = 0;

        public synchronized void add(long value) {
            this.count += value;
        }
    }

    @Data
    public static class TwoSums {

        private int sum1 = 0;
        private int sum2 = 0;

        private Integer sum1Lock = 1;
        private Integer sum2Lock = 2;


        public void add(int var1, int var2) {
            /*synchronized (this){
                this.sum1 += var1;
                this.sum2 += var2;
            }*/


            synchronized (this.sum1Lock) {
                this.sum1 += var1;
            }

            synchronized (this.sum2Lock) {
                this.sum2 += var2;
            }
        }
    }

}
