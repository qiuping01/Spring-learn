package com;

import com.test.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //1、加载spring.xml
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("spring.xml");
        //2、通过运行时类获取对象
        Student bean = ioc.getBean(Student.class);
        System.out.println(bean);
    }
}