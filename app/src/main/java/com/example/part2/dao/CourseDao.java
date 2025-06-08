package com.example.part2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.part2.domain.Course;
import com.example.part2.domain.CourseWStudents;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM courses ORDER BY courseCode ASC")
    LiveData<List<Course>> getAllCourses();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Course course);

    @Delete
    void deleteCourse(Course course);

    @Transaction
    @Query("SELECT * FROM courses WHERE courseId = :id")
    LiveData<CourseWStudents> getCourseWStudents(int id);
}
