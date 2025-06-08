package com.example.part2.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.part2.R;
import com.example.part2.dao.StudentDao;
import com.example.part2.db.AppDatabase;
import com.example.part2.domain.Student;
import com.google.android.material.textfield.TextInputEditText;

public class EditStudentActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT_ID = "student_id";
    public static final String EXTRA_COURSE_ID  = "course_id";

    private TextInputEditText etName, etEmail, etUsername;
    private StudentDao studentDao;
    private int studentId, courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        studentDao = AppDatabase.getInstance(this).studentDao();

        etName     = findViewById(R.id.etName);
        etEmail    = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);

        studentId = getIntent().getIntExtra(EXTRA_STUDENT_ID, -1);
        courseId  = getIntent().getIntExtra(EXTRA_COURSE_ID,  -1);

        new Thread(() -> {
            Student s = studentDao.findById(studentId);
            runOnUiThread(() -> {
                etName.   setText(s.getName());
                etEmail.  setText(s.getEmail());
                etUsername.setText(s.getUserName());
            });
        }).start();

        Button btnSave = findViewById(R.id.btnSaveStudent);
        btnSave.setOnClickListener(v -> saveAndFinish());
    }

    private void saveAndFinish() {
        String name  = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String uname = etUsername.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || uname.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            Student s = new Student(name, email, uname);
            s.setStudentId(studentId);
            studentDao.update(s);

            runOnUiThread(() -> {
                Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}
