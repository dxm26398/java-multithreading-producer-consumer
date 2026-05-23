// Dipinsa Marasini
// Java Multithreading — Producer Consumer
// Advanced Application Development | UT Arlington

public class Producer extends Thread {
    private Buffer buffer;
    private String color;

    public Producer(Buffer buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        String[] words = {
                "Elephant",
                "Computer",
                "Multithreading",
                "Producer",
                "Consumer",
                "Synchronization",
                "ReentrantLock",
                "Programming",
                "BufferData",
                "ThreadSafe",
                "JavaLanguage",
                "Concurrent",
                "ConditionVar",
                "ExecutionFlow"
        };

        for (String word : words) {
            buffer.produce(word, color, getName());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        buffer.setProducerFinished();
    }
}
