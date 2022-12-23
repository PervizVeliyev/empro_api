package com.example.EmproApi.mapper;

import com.example.EmproApi.entity.Student;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Student student = new Student();
        student.setEmail(rs.getString("email"));
        student.setStudentFullName(rs.getString("studentFullName"));
        student.setExamDate(rs.getString("examDate"));
        student.setPoint(rs.getString("point"));
        student.setSubjectName(rs.getString("subjectName"));
        return student;
    }
}
