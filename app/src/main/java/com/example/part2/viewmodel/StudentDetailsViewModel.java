package com.example.part2.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.*;
import com.example.part2.dao.StudentDao;
import com.example.part2.db.AppDatabase;
import com.example.part2.domain.StudentWCourses;

public class StudentDetailsViewModel extends AndroidViewModel {
    private final LiveData<StudentWCourses> studentWithCourses;

    public StudentDetailsViewModel(@NonNull Application app, int studentId) {
        super(app);
        StudentDao dao = AppDatabase.getInstance(app).studentDao();
        studentWithCourses = dao.getStudentWithCourses(studentId);
    }

    public LiveData<StudentWCourses> getStudentWithCourses() {
        return studentWithCourses;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final Application app;
        private final int studentId;
        public Factory(Application app, int studentId){
            this.app = app;
            this.studentId = studentId;
        }
        @SuppressWarnings("unchecked")
        @NonNull @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
            return (T) new StudentDetailsViewModel(app, studentId);
        }
    }
}
