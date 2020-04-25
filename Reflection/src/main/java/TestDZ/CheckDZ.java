package TestDZ;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class CheckDZ {
    public static void check(String path) throws Exception {
        File file = new File(path);
        String[] files = file.list();

        for (String name : files) {
            String[] mass = name.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(name, new Exception());
            }

            Class c = URLClassLoader.newInstance(new URL[] {file.toURL()}).loadClass(mass[0]);
            Object obj = c.newInstance();

            Method method = c.getDeclaredMethod("isNegative", int.class);
            method.setAccessible(true);
            boolean result = (boolean) method.invoke("isNegative", -1);
            if (!result) {
                throw new RuntimeException("Method isNegative work incorrect");
            } else {
                System.out.println("Method isNegative work correct");
            }

            method = c.getDeclaredMethod("calculate", int.class, int.class, int.class, int.class);
            method.setAccessible(true);
            int resultInt = (int) method.invoke("calculate", 2, 3, 4, 3);
            if (resultInt != 8) {
                throw new RuntimeException("Method calculate(int) work incorrect");
            } else {
                System.out.println("Method calculate(int) work correct");
            }

            method = c.getDeclaredMethod("calculate", float.class, float.class, float.class, float.class);
            method.setAccessible(true);
            float resultFloat = (float) method.invoke("calculate", 2f, 3f, 4f, 4f);
            if (resultFloat != 8f) {
                throw new RuntimeException("Method calculate(float) work incorrect");
            } else {
                System.out.println("Method calculate(floor) work correct");
            }

            method = c.getDeclaredMethod("checkTwoNumbers", int.class, int.class);
            method.setAccessible(true);
            result = (boolean) method.invoke("checkTwoNumbers", 10, 15);
            if (result) {
                throw new RuntimeException("Method checkTwoNumbers work incorrect");
            } else {
                System.out.println("Method checkTwoNumbers work correct");
            }

            method = c.getDeclaredMethod("isLeapYear", int.class);
            method.setAccessible(true);
            result = (boolean) method.invoke("isLeapYear", 100);
            if (result) {
                throw new RuntimeException("Method isLeapYear work incorrect");
            } else {
                System.out.println("Method isLeapYear work correct");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        check("src/main/java/class");
    }
}
