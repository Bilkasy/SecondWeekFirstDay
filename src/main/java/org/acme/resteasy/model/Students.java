package org.acme.resteasy.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@JsonSerialize
public class Students {
    private long id;
    private String name;
    private int enrollmentYear;
    private String major;

    public Students() {
    }

    public Students(long id, String name, int enrollmentYear , String major) {
        this.id = id;
        this.name = name;
        this.enrollmentYear = enrollmentYear;
        this.major = major;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
