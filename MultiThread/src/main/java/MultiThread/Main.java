package MultiThread;

public class Main {
    public static final Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        int priority = 0;
        PrintThread threadOne = new PrintThread('A', ++priority);
        PrintThread threadTwo = new PrintThread('B', ++priority);
        PrintThread threadThree = new PrintThread('C', ++priority);
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadOne.join();
        threadTwo.join();
        threadThree.join();
    }
}
