package com.example.EmproApi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student{
    private String email;

    private String studentFullname;

    private String examDate;

    private String point;

    private String subjectName;
}
