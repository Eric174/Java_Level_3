package InOut;

import java.io.Serializable;

public class Human implements Serializable {
    private int age;
    private String name;
    private String gender;

    public Human(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public String getAllInfo() {
        return "Name: " + name + ", gender: " + gender + ", age: " + age;
    }
}
