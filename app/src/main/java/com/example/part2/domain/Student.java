package com.example.part2.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int studentId;
    private String name;
    private String email;

    @NonNull
    @ColumnInfo(name = "username")
    private String userName;

    public Student(String name, String email, @NonNull String userName) {
        this.name     = name;
        this.email    = email;
        this.userName = userName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }
}
