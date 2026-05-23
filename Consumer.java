// Dipinsa Marasini
// Java Multithreading — Producer Consumer
// Advanced Application Development | UT Arlington

public class Consumer extends Thread {
    private Buffer buffer;
    private String color;

    public Consumer(Buffer buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            String value = buffer.consume();

            if (value == null) {
                break;
            }

            int length = value.length();
            String reversed = new StringBuilder(value).reverse().toString();

            System.out.println(color + getName()
                    + " consumed: " + value
                    + " | Length: " + length
                    + " | Reversed: " + reversed
                    + Main.RESET);

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
