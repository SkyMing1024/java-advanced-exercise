package com.sky.autoconfig;

import com.sky.entity.Klass;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "auto.edu.school")
public class SchoolProperties {

    String schoolName;

    List<Klass> klassList = new ArrayList<>();

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Klass> getKlassList() {
        return klassList;
    }

    public void setKlassList(List<Klass> klassList) {
        this.klassList = klassList;
    }
}
