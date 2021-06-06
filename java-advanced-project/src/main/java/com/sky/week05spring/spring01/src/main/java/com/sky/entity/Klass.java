package com.sky.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("kkk")
public class Klass {

    @Autowired
    Student student;

    public Klass(Student student) {
        this.student = student;
        this.add(student);
    }

    List<Student> studentList = new ArrayList<>();

    public Student add(Student student){
        this.studentList.add(student);
        return student;
    }

    public void Show(){
        System.out.println("展示学生信息：");
        studentList.stream().forEach(e->{
            System.out.println(e);
        });
    }
}
