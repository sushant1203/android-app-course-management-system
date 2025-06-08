package com.example.part2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Transaction;

import com.example.part2.domain.Student;
import com.example.part2.domain.StudentWCourses;

@Dao
public interface StudentDao {

    @Insert
    long insert(Student student);

    @Query("SELECT * FROM students WHERE username = :uname LIMIT 1")
    Student findByUsername(String uname);

    @Query("SELECT * FROM students WHERE studentId = :id LIMIT 1")
    Student findById(int id);

    @Update
    void update(Student student);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :id")
    LiveData<StudentWCourses> getStudentWithCourses(int id);
}
