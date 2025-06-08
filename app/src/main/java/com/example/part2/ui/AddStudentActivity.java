package com.example.part2.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.part2.R;
import com.example.part2.dao.EnrollmentDao;
import com.example.part2.dao.StudentDao;
import com.example.part2.db.AppDatabase;
import com.example.part2.domain.Enrollment;
import com.example.part2.domain.Student;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.Executors;

public class AddStudentActivity extends AppCompatActivity {
    public static final String EXTRA_COURSE_ID = "course_id";

    private TextInputEditText etName, etEmail, etUsername;
    private StudentDao studentDao;
    private EnrollmentDao enrollmentDao;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        AppDatabase db = AppDatabase.getInstance(this);
        studentDao    = db.studentDao();
        enrollmentDao = db.enrollmentDao();


        etName     = findViewById(R.id.etName);
        etEmail    = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);


        courseId = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);


        Button btn = findViewById(R.id.btnAddStudent);
        btn.setOnClickListener(v -> addStudent());
    }

    private void addStudent() {
        String name  = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String uname = etUsername.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || uname.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }


        Executors.newSingleThreadExecutor().execute(() -> {
            Student existing = studentDao.findByUsername(uname);

            if (existing != null
                    && enrollmentDao.countFor(courseId, existing.getStudentId()) > 0) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Student already enrolled", Toast.LENGTH_SHORT).show()
                );
                return;
            }

            long studentId = existing != null
                    ? existing.getStudentId()
                    : studentDao.insert(new Student(name, email, uname));

            enrollmentDao.insert(new Enrollment(courseId, (int) studentId));

            runOnUiThread(() -> {
                Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
