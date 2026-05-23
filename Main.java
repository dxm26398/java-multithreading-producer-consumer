// Dipinsa Marasini
// Java Multithreading — Producer Consumer
// Advanced Application Development | UT Arlington

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Producer producer = new Producer(buffer, RED);
        Consumer consumer1 = new Consumer(buffer, BLUE);
        Consumer consumer2 = new Consumer(buffer, GREEN);
        Consumer consumer3 = new Consumer(buffer, PURPLE);

        producer.setName("Producer");
        consumer1.setName("Consumer 1");
        consumer2.setName("Consumer 2");
        consumer3.setName("Consumer 3");

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nAll threads finished.");
    }
}
