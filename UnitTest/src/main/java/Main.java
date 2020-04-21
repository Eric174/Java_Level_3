import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static int[] cutArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("Inappropriate input array");
        }
        ArrayList<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toCollection(ArrayList::new));
        int lastIndex = list.lastIndexOf(4);
        if (lastIndex == -1) {
            throw new RuntimeException("The array does not contain \"4\"");
        }
        if (lastIndex == array.length) {
            return new int[0];
        }
        lastIndex++;
        int length = array.length - lastIndex;
        int[] resultArray = new int[length];
        System.arraycopy(array,lastIndex,resultArray,0, length);
        return resultArray;
    }

    public static boolean checkArray(int[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        ArrayList<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toCollection(ArrayList::new));
        return list.lastIndexOf(1) != -1 && list.lastIndexOf(4) != -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 1, 5, 6, 4, 7, 1, 9, 8};
        System.out.println(Arrays.toString(cutArray(arr)));
        System.out.println(checkArray(new int[] {1, 1, 4, 1}));
    }
}
