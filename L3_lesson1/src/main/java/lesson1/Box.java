package lesson1;

import java.util.ArrayList;
import java.util.Collections;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<T>();

    private ArrayList<T> getFruits() {
        return fruits;
    }

    public int getCountFruits() {
        return fruits.size();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public void addFruit(T fruit, int count) {
        if (count >= 0) {
            for (int i = 0; i < count; i++) {
                addFruit(fruit);
            }
        }
    }

    public float getWeight() {
        float weight = 0f;
        for (int i = 0; i < fruits.size(); i++) {
            weight += fruits.get(i).getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void pourToBox(Box<T> box) {
        if (fruits.size() != 0 && box.getFruits().size() != 0) {
            box.getFruits().addAll(fruits);
            fruits.removeAll(this.getFruits());
        }
    }
}
