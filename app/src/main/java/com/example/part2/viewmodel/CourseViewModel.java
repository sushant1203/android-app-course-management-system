package com.example.part2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.part2.dao.CourseDao;
import com.example.part2.db.AppDatabase;
import com.example.part2.domain.Course;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseViewModel extends AndroidViewModel {
    private final CourseDao courseDao;
    private final LiveData<List<Course>> allCourses;
    private final ExecutorService ioExecutor;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        courseDao    = db.courseDao();
        allCourses   = courseDao.getAllCourses();
        ioExecutor   = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insert(Course course) {
        ioExecutor.execute(() -> courseDao.insert(course));
    }

    public void delete(Course course) {
        ioExecutor.execute(() -> courseDao.deleteCourse(course));
    }
}
