package com;

import com.test.entity.Class;
import com.test.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
//        //1、加载spring.xml
//        ApplicationContext ioc =
//                new ClassPathXmlApplicationContext("spring.xml");
//        //2、通过id获取对象
//        Student bean = (Student) ioc.getBean("student");
//        System.out.println(bean);
//        Student bean2 = (Student) ioc.getBean("student2");
//        System.out.println(bean2);
//        Student bean3 = (Student) ioc.getBean("student3");
//        System.out.println(bean3);
//        Student bean4 = (Student) ioc.getBean("student4");
//        System.out.println(bean4);

        Class clazz = new Class();
        clazz.setId(1);
        clazz.setName("Java 1班");
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(22);
        student.setClazz(clazz);
        System.out.println(student);

    }
}