// Dipinsa Marasini
// Java Multithreading — Producer Consumer
// Advanced Application Development | UT Arlington

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private Queue<String> queue = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private boolean producerFinished = false;

    public void produce(String value, String color, String threadName) {
        lock.lock();
        try {
            queue.add(value);
            System.out.println(color + threadName + " produced: " + value + Main.RESET);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String consume() {
        lock.lock();
        try {
            while (queue.isEmpty() && !producerFinished) {
                notEmpty.await();
            }

            if (queue.isEmpty() && producerFinished) {
                return null;
            }

            return queue.remove();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void setProducerFinished() {
        lock.lock();
        try {
            producerFinished = true;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
