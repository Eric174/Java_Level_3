package MultiThread;

public class PrintThread extends Thread {
    private static int current = 1;
    public static int counter = 0;
    private static final Object lock = new Object();

    private final char symbol;
    private final int priority;
    private  int iteration = 5;

    public PrintThread(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
        counter++;
    }


    @Override
    public synchronized void run() {

        synchronized (lock) {
            while (iteration > 0) {
                while (current != priority) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(symbol);
                current++;
                if (current > counter) {
                    current = 1;
                }
                lock.notifyAll();
                iteration--;
            }
        }
    }
}
