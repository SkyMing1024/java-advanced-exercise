package com.sky;

import com.sky.entity.Klass;
import com.sky.entity.School;
import com.sky.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class BeanTest {

    @Test
    public void testXml() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student studentByXml = (Student) context.getBean("studentByXml");
        System.out.println(studentByXml);

        Object klass1 = context.getBean("klass1");
        System.out.println(klass1);
    }

    @Test
    public void testAnnotation() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object school = context.getBean("school");
        System.out.println(school);
    }

    @Test
    public void testJavaConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Object student = context.getBean("student3");
        System.out.println(student);
    }
}
