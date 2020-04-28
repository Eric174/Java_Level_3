import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter matrix width");
        int width = scanner.nextInt();
        System.out.println("Enter matrix height");
        int height = scanner.nextInt();
        int num = 0;
        int[][] array = new int[height][width];
        int indexHeight = 0;
        int indexWidth = 0;
        int count = 0;
        int all = height * width;
        try {
            while (num < all) {
                while (indexWidth + 1 < width - count && num < all) {
                    array[indexHeight][indexWidth++] = ++num;
                }
                while (indexHeight + 1 < height - count && num < all) {
                    array[indexHeight++][indexWidth] = ++num;
                }
                while (indexWidth > count && num < all) {
                    array[indexHeight][indexWidth--] = ++num;
                }
                count++;
                while (indexHeight > count && num < all) {
                    array[indexHeight--][indexWidth] = ++num;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println(indexHeight);
            System.out.println(indexWidth);
            throw new RuntimeException("Check the algorithm");
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
