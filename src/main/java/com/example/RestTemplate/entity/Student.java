package com.example.RestTemplate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student{
    private String email;

    private String studentFullName;

    private String examDate;

    private String point;

    private String subjectName;
}
