// app/src/main/java/com/example/part2/domain/Course.java
package com.example.part2.domain;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "courses",
        indices = {
                @Index(value = "courseCode", unique = true)
        }
)
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseId;

    private String courseCode;
    private String courseName;
    private String lecturerName;

    public Course(String courseCode, String courseName, String lecturerName) {
        this.courseCode   = courseCode;
        this.courseName   = courseName;
        this.lecturerName = lecturerName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
}
