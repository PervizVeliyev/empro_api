package com.example.EmproApi.repository;

import com.example.EmproApi.entity.Student;
import com.example.EmproApi.mapper.StudentRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Student> findByExamDate(String date)
    {
        return jdbcTemplate.query("""
                select email, studentFullname, examDate, point, subjectName from (select u.email as email, concat(substring_index(u.lastname, ' (', 1), ' ', u.firstname, ' ',substring_index(substring_index(u.lastname, ' (', -1), ')', 1)) as studentFullname,\s
                \t\tfrom_unixtime(gg.timecreated, '%d/%m/%Y') as examDate, c.name as groupname, round(gg.finalgrade) as point, cctrs2.name as subjectName
                \t\tfrom mdl_grade_grades gg
                \t\tINNER JOIN mdl_user u ON u.id = gg.userid
                \t\tINNER JOIN mdl_cohort_members cm ON cm.userid=u.id
                \t\tINNER JOIN mdl_cohort c ON c.id=cm.cohortid
                \t\tINNER JOIN mdl_grade_items gi ON gi.id = gg.itemid
                \t\tINNER JOIN mdl_course crs ON crs.id = gi.courseid
                \t\tINNER JOIN mdl_course_categories cctrs ON cctrs.id = crs.category
                \t\tINNER JOIN mdl_course_categories cctrs2 ON cctrs2.id = cctrs.parent
                \t\twhere gg.finalgrade is not null AND gi.itemmodule = 'quiz'
                \t\torder by gg.timecreated) as temp
                \t\twhere temp.examDate = ?;""",new Object[] {date}, new StudentRowMapper());
    }
}
