package com.example.part2.domain;

import androidx.room.Entity;
import androidx.room.ForeignKey;


@Entity(
        primaryKeys = {"courseId","studentId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "courseId",
                        childColumns  = "courseId",
                        onDelete      = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "studentId",
                        childColumns  = "studentId",
                        onDelete      = ForeignKey.CASCADE
                )
        }
)
public class Enrollment {
    public int courseId;
    public int studentId;

    public Enrollment(int courseId, int studentId) {
        this.courseId  = courseId;
        this.studentId = studentId;
    }
}
