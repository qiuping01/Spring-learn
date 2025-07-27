package com;

import com.test.entity.Class;
import com.test.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //1、加载spring.xml
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("spring.xml");
        //2、通过id获取对象
        Student student = (Student) ioc.getBean("student");
        System.out.println(student);
        Class clazz = (Class) ioc.getBean("clazz");
        System.out.println(clazz);


    }
}