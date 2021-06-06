package com.sky;

import com.sky.entity.Klass;
import com.sky.entity.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {

    @Test
    public void testXml(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student studentByXml = (Student) context.getBean("studentByXml");
        System.out.println(studentByXml);

        Klass klass =(Klass) context.getBean("kkk");
        klass.add(studentByXml);
        klass.Show();
    }

    public void testAnnotation(){

    }
}
