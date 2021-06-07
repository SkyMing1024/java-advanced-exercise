package com.sky;

import com.sky.entity.Department;
import com.sky.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean("dept1")
    public Department getDept() {
        return new Department(1, "研发部");
    }

    @Bean("student3")
    public Student getStuent() {
        return new Student("Stephen", "Curry", 30);
    }

}
