package com.sky.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("klass")
@Data
public class Klass {

    Student student;

    List<Student> studentList = new ArrayList<>();

    public Student add(Student student) {
        this.studentList.add(student);
        return student;
    }

    public void Show() {
        System.out.println("展示学生信息：");
        studentList.stream().forEach(e -> {
            System.out.println(e);
        });
    }
}
