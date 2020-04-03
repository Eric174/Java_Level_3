package lesson1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    //1
    public static boolean changePlace(String[] array, int first, int second) {
        if (array.length == 0 || first > array.length - 1 || second > array.length - 1 ||
                first  < 0 || second < 0) {
            return false;
        }
        String bufer =  array[second];
        array[second]= array[first];
        array[first] = bufer;
        return true;
    }

    //2
    public static ArrayList<String>  getArraList(String[] array) {
        ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, array);
        return list;
    }

    public static void main(String[] args) {
        //1
        String[] stringArray = {"One", "Two", "Three", "Four", "Five"};
        System.out.println(changePlace(stringArray, 1, 6) ? "Array elements have changed " : "Array elements have not changed");
        System.out.println(Arrays.toString(stringArray));
        System.out.println(changePlace(stringArray, 1, 3) ? "Array elements have changed " : "Array elements have not changed");
        System.out.println(Arrays.toString(stringArray) + "\n");

        //2
        ArrayList<String> list = getArraList(stringArray);
        System.out.println(list);
        stringArray[0] = "Zero";
        list.add("Nothing");
        System.out.println(Arrays.toString(stringArray));
        System.out.println(list + "\n");

        //3
        Box<Apple> boxApple = new Box<Apple>();
        boxApple.addFruit(new Apple(), 12);
        System.out.println("Weight apples in  box : " + boxApple.getWeight());

        Box<Orange> boxOrangeOne = new Box<Orange>();
        boxOrangeOne.addFruit(new Orange(), 8);
        System.out.println("Weight oranges in first box : " + boxOrangeOne.getWeight());

        Box<Orange> boxOrangeTwo = new Box<Orange>();
        boxOrangeTwo.addFruit(new Orange(), 12);
        System.out.println("Weight oranges in second box : " + boxOrangeTwo.getWeight());

        System.out.println("Compare apples weight and oranges in first box : " + boxApple.compare(boxOrangeOne));
        System.out.println("Compare apples weight and oranges in second box : " + boxApple.compare(boxOrangeTwo));

        boxOrangeOne.pourToBox(boxOrangeTwo);
        System.out.println("Weight oranges in first box : " + boxOrangeOne.getWeight() + " count oranges in first box : " + boxOrangeOne.getCountFruits());
        System.out.println("Weight oranges in second box : " + boxOrangeTwo.getWeight() + " count oranges in second box : " + boxOrangeTwo.getCountFruits());
    }
}
