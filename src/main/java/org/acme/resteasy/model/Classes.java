package org.acme.resteasy.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
@JsonSerialize
public class Classes {
    private long id;
    private String name;
    private List<Students> classStudentsList;

    public Classes(long id, String name, List<Students> classStudentsList) {
        this.id = id;
        this.name = name;
        this.classStudentsList = classStudentsList;
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

    public List<Students> getClassStudentsList() {
        return classStudentsList;
    }

    public void setClassStudentsList(List<Students> classStudentsList) {
        this.classStudentsList = classStudentsList;
    }
}
