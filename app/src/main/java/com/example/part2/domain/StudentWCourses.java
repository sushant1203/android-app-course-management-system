package com.example.part2.domain;


import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class StudentWCourses {
    @Embedded
    public Student student;

    @Relation(
            parentColumn = "studentId",
            entityColumn = "courseId",
            associateBy = @Junction(Enrollment.class)
    )
    public List<Course> courses;
}
