# Spring

## 1 spring 介绍

Spring框架是Java开发的行业标准。

spring是一套完整的生态，全家桶，Spring Boot和Spring Cloud。

Spring全家桶的各个模块都是基于Spring Framework（初代）衍生而来的，它包含了loC控制反转，DI依赖注入，AOP面向切面编程，Context上下文管理，bean管理，Spring Web MVC等众多功能，其他的Spring家族成员都需要依赖于Spring Framework。

Spring Framework是一个设计层面的框架，通过分层思想来实现组件之间的解耦合，开发者可以根据需求选择不同的组件，并且可以非常方便地进行集成，这一特性使得企业开发变得更加简单方便。

---

## 2 Spring 两大核心机制

- loC(控制反转)

- AOP(面向切面)

### 2.1 loC（控制反转）

控制反转，到底在反转什么？

传统开发中需要获取对象时，通常由开发者手动创建对象，但是在Spring框架中创建对象的工作不再由开发者来完成，而是交给IoC容器来创建，我们直接获取即可，整个流程完成反转，控制反转。

```java
//加载MyBatis配置文件
InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
//获取SqlSession
SqlSession sqlSession = sqlSessionFactory.openSession();
//获取实现接口的代理对象
UserRepository repository = sqlSession.getMapper(UserRepository.class);
```

上述对象都不需要手动创建了。

传统创建对象：

Student实体类：

```java
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    // 省略getter/setter/toString
}
```

Test:

```java
public static void main(String[] args) {
    Student student = new Student();
    System.out.println(student);
}
```

##### 1、pom.xml 引入Spring依赖（Maven项目），Springboot项目不需要引入

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.3.21</version>
</dependency>
```

##### 2、创建配置文件，spring.xml，在配置文件中配置bean标签，Ioc容器通过加载bean标签创建对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
      http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
	<!--开始添加对象-->
    <bean id="student2" class="com.test.entity.Student"></bean>

</beans>
```

##### 3、启动Spring获取IoC创建的对象

可以理解IoC就是一个缓冲池，里面有很多对象，加载出来就可以了

```java
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
```

在配置文件中定义需要的bean

spring加载配置文件，读取配置文件，获取到需要创建的bean的信息，创建bean（使用Java的哪个技术来创建的？）

实体类得默认无参构造器，因为Spring IoC默认调用无参构造创建对象

```java
有参覆盖后报错：不存在Student类的无参构造
com.test.entity.Student.<init>()
```

反射：

```java
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
```

为什么默认使用无参构造？而不使用有参构造？

不要刻意的以纯技术的思维去想，是因为无法获取有参构造，有参的话`null`传什么？

```java
Class<?> aClass = Class.forName(string);
            Constructor<?> constructor = aClass.getConstructor(null);
            System.out.println(constructor.newInstance(null));
```

再者

```java
public class Test {
    public static void main(String[] args) {
        test("com.test.entity.Student");
        test("com.test.entity.Teacher");
        test("com.test.entity.Class");
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
```

`aClass.getConstructor(param:);`中的参数传什么？

很显然，现在连类都不知道，怎么可能知道类里面的结构，怎么知道哪个构造器？

无参就不需要知道这些。

##### 4、给bean的属性赋值

```xml
<!--开始添加对象-->
<bean id="student2" class="com.test.entity.Student">
    <property name="id" value="1"></property>
    <property name="name" value="张三"></property>
    <property name="age" value="22"></property>
</bean>
```

IoC容器从创建对象到给对象赋值，都是通过反射机制来实现的，通过反射机制获取类的无参构造函数，进而创建对象，再通过反射机制调用类的setter方法，为对象的属性赋值。 

IoC中的bean，可以通过id来获取，也可以通过运行时类来获取。

通过运行时类获取的方式，必须保证IoC中只能有一个该类型的bean，当IoC有多个同类型bean时，需要通过id来获取这些bean。

### 2.2 AOP（面向切面）



































