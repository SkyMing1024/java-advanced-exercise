package com.sky.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("school")
@Data
@ToString
public class School {
    String schoolName;

    List<Klass> klassList = new ArrayList<>();

    public void start() {
        System.out.println("上学了");
    }
}
