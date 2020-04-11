package InOut;
/*
 Job with files
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println("First task");
        readFileInArray("1.txt");
        System.out.println("Second task");
        combineFiles(new String[] {"1.txt", "2.txt", "3.txt", "4.txt", "5.txt",});
        readFileInArray("total.txt");
        System.out.println("Third task");
        long time = System.currentTimeMillis();
        readLargeFile("LargeFile.txt", 6);
        long executeTime = System.currentTimeMillis() - time;
        System.out.println("Time in milliseconds to execute method : " + executeTime);
        System.out.println("Time in seconds to execute method : " + TimeUnit.MILLISECONDS.toSeconds(executeTime));

    }

    public static void readFileInArray(String path) {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            byte[] array = new byte[64];
            int x;
            while ((x = inputStream.read(array)) > 0) {
                System.out.print(new String(array, 0 , x));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void combineFiles(String[] paths) {
        ArrayList<InputStream> list = new ArrayList<>();
        for (String path : paths) {
            try {
                list.add(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                System.out.println("Not found file with name " + path);
            }
        }

        try (SequenceInputStream stream = new SequenceInputStream(Collections.enumeration(list));
             FileOutputStream outputStream = new FileOutputStream("total.txt")) {
            byte[] array = new byte[512];
            int x;
            while ((x = stream.read(array)) > 0) {
                outputStream.write(array,0, x);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readLargeFile(String path, int page) {
        final int pageSize = 1800;
        byte[] array = new byte[pageSize];
        int x;
        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            file.seek((page - 1) * pageSize);
            x = file.read(array);
            System.out.print(new String(array, 0 , x));
            System.out.println("\n Page number : " + page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
