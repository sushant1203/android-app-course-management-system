package com.example.part2.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.R;
import com.example.part2.dao.CourseDao;
import com.example.part2.db.AppDatabase;
import com.example.part2.domain.Course;
import com.example.part2.viewmodel.CourseViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateCourseActivity extends AppCompatActivity {
    private CourseViewModel courseViewModel;
    private ExecutorService ioExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        courseViewModel = new ViewModelProvider(this)
                .get(CourseViewModel.class);
        ioExecutor = Executors.newSingleThreadExecutor();

        EditText etCode     = findViewById(R.id.etCourseCode);
        EditText etName     = findViewById(R.id.etCourseName);
        EditText etLecturer = findViewById(R.id.etLecturerName);
        Button btnCreate    = findViewById(R.id.btnCreateCourse);

        btnCreate.setOnClickListener(v -> {
            String code     = etCode.getText().toString().trim();
            String name     = etName.getText().toString().trim();
            String lecturer = etLecturer.getText().toString().trim();

            if (code.isEmpty() || name.isEmpty() || lecturer.isEmpty()) {
                Toast.makeText(this,
                        "All fields are required",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Course course = new Course(code, name, lecturer);

            ioExecutor.execute(() -> {
                CourseDao dao = AppDatabase.getInstance(this).courseDao();
                long newId = dao.insert(course);

                runOnUiThread(() -> {
                    if (newId == -1) {
                        Toast.makeText(
                                CreateCourseActivity.this,
                                "Course code already exists",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        finish();
                    }
                });
            });
        });
    }
}
