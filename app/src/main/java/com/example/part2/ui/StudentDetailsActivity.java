package com.example.part2.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.part2.R;
import com.example.part2.viewmodel.StudentDetailsViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class StudentDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT_ID = "student_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());

        int studentId = getIntent().getIntExtra(EXTRA_STUDENT_ID, -1);

        TextView tvName     = findViewById(R.id.tvStudentName);
        TextView tvEmail    = findViewById(R.id.tvStudentEmail);
        TextView tvUsername = findViewById(R.id.tvStudentUsername);

        RecyclerView rv = findViewById(R.id.recyclerCourses);
        CourseListAdapter adapter = new CourseListAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        StudentDetailsViewModel vm = new ViewModelProvider(
                this,
                new StudentDetailsViewModel.Factory(getApplication(), studentId)
        ).get(StudentDetailsViewModel.class);

        vm.getStudentWithCourses().observe(this, swc -> {
            if (swc != null) {
                tvName.setText(swc.student.getName());
                tvEmail.setText(swc.student.getEmail());
                tvUsername.setText(swc.student.getUserName());
                adapter.submitList(swc.courses);
            }
        });
    }
}
