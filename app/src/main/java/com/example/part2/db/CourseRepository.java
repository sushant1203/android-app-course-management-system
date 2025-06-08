package com.example.part2.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.part2.dao.CourseDao;
import com.example.part2.domain.Course;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class CourseRepository {
    private final CourseDao courseDao;
    private final ExecutorService executor;

    public CourseRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        courseDao = db.courseDao();
        executor = AppDatabase.databaseWriteExecutor;
    }

    public LiveData<List<Course>> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void insertCourse(Course course) {
        executor.execute(() -> courseDao.insert(course));
    }

    public void deleteCourse(Course course) {
        executor.execute(() -> courseDao.deleteCourse(course));
    }
}
