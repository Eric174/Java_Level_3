package Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class TestClass {

    public static void start(Class c) {
        int beforeCount = 0;
        int afterCount = 0;
        Method beforeMethod = null;
        Method afterMethod = null;
        Method[] methods = c.getDeclaredMethods();
        ArrayList<Method> list = new ArrayList<>(methods.length);
        for (Method method: methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeMethod = method;
                if (++beforeCount > 1) {
                    throw new RuntimeException("BeforeSuite annotations more than one");
                }
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                afterMethod = method;
                if (++afterCount > 1) {
                    throw new RuntimeException("AfterSuite annotations more than one");
                }
            } else if (method.getAnnotation(Test.class) != null) {
                list.add(method);
            }
        }

        list.sort(Comparator.comparingInt(obj -> obj.getAnnotation(Test.class).priority()));

        if (beforeMethod != null) {
            list.add(0, beforeMethod);
        }

        if (afterMethod != null) {
            list.add(afterMethod);
        }
        Object obj = null;
        try {
            obj = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Method method: list) {
            if (Modifier.isPrivate(method.getModifiers())) {
                method.setAccessible(true);
            }
            try {
                method.invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
