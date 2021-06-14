package com.sky.autoconfig;

import com.sky.entity.Klass;
import com.sky.entity.School;
import com.sky.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableConfigurationProperties({StudentProperties.class,KlassProperties.class,SchoolProperties.class})
public class EducationAutoConfiguration {
    @Autowired
    StudentProperties properties;
    @Autowired
    KlassProperties klassProperties;
    @Autowired
    SchoolProperties schoolProperties;
    @Bean
    public Student student(){
        log.info("装载student");
        Student student = new Student(properties.firstName, properties.lastName, properties.age);
        return student;
    }
    @Bean
    @ConditionalOnClass(Student.class)
    public Klass klass(){
        log.info("装载klass");
        Klass klass = new Klass();
        klass.setKlassName(klassProperties.klassName);
        klass.setStudent(klassProperties.getStudent());
        return klass;
    }
    @Bean
    @ConditionalOnClass(Klass.class)
    public School school(){
        log.info("scholl");
        School school = new School();
        school.setSchoolName(schoolProperties.schoolName);
        return school;
    }
}
