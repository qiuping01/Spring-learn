package com;

import com.test.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;


public class Test {
    public static void main(String[] args) {
        test("com.test.entity.Student");
    }

    // 反射
    public static void test(String string) {
        try {
            Class<?> aClass = Class.forName(string);
            Constructor<?> constructor = aClass.getConstructor(null);
            System.out.println(constructor.newInstance(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
