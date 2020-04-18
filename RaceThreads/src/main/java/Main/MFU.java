package Main;

public class MFU {
    private final Object printObject = new Object();
    private final Object scanObject = new Object();

    public void print(String doc, int n) {
        synchronized (printObject) {
            System.out.println("Начало печати");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Конец печати");
        }
    }

    public void scan(String doc, int n, boolean printRequired) {
        synchronized (scanObject) {
            System.out.println("Начало сканирования");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Конец сканирования");
            if (printRequired) print(doc, n);
        }
    }

    public static void main(String[] args) {
        final MFU mfu = new MFU();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("doc1", 10);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("doc2", 5);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("doc3", 10);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("doc4", 10, true);
            }
        }).start();
    }
}
