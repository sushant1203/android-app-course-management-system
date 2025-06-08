package com.example.part2.domain;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CourseWStudents {
    @Embedded public Course course;

    @Relation(
            parentColumn = "courseId",
            entityColumn = "studentId",
            associateBy = @Junction(Enrollment.class)
    )
    public List<Student> students;
}
