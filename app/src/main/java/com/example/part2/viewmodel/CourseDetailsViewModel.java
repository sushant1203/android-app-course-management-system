package com.example.part2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.dao.CourseDao;
import com.example.part2.db.AppDatabase;
import com.example.part2.domain.CourseWStudents;

public class CourseDetailsViewModel extends AndroidViewModel {
    private final LiveData<CourseWStudents> courseWStudents;

    public CourseDetailsViewModel(@NonNull Application app, int courseId) {
        super(app);
        CourseDao dao = AppDatabase.getInstance(app).courseDao();
        courseWStudents = dao.getCourseWStudents(courseId);
    }

    public LiveData<CourseWStudents> getCourseWStudents() {
        return courseWStudents;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final Application app;
        private final int courseId;
        public Factory(Application app, int courseId) {
            this.app = app;
            this.courseId = courseId;
        }
        @NonNull @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new CourseDetailsViewModel(app, courseId);
        }
    }
}
