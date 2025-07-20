package com;

import com.test.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //1、加载spring.xml
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("spring.xml");
        //2、通过id获取对象
        Student student2 = (Student) ioc.getBean("student2");
        System.out.println(student2);
    }
}
