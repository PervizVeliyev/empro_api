package com.example.RestTemplate.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Student implements Serializable {
    private String email;

    private String studentFullName;

    private String examDate;

    private String point;

    private String subjectName;

    @JsonCreator
    public Student(@JsonProperty("email") String email,@JsonProperty("studentFullName") String studentFullName,@JsonProperty("examDate") String examDate,@JsonProperty("point") String point,@JsonProperty("subjectName") String subjectName) {
        this.email = email;
        this.studentFullName = studentFullName;
        this.examDate = examDate;
        this.point = point;
        this.subjectName = subjectName;
    }

    public Student() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
