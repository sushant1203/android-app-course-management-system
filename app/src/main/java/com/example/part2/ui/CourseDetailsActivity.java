package com.example.part2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;
import com.example.part2.viewmodel.CourseDetailsViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_COURSE_ID = "course_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int courseId = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);

        TextView tvCode     = findViewById(R.id.tvCourseCode);
        TextView tvName     = findViewById(R.id.tvCourseName);
        TextView tvLecturer = findViewById(R.id.tvLecturerName);

        RecyclerView rv = findViewById(R.id.recyclerStudents);
        StudentListAdapter adapter = new StudentListAdapter(courseId);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAddStudent);
        fab.setOnClickListener(v -> {
            Intent i = new Intent(this, AddStudentActivity.class);
            i.putExtra(AddStudentActivity.EXTRA_COURSE_ID, courseId);
            startActivity(i);
        });

        CourseDetailsViewModel vm = new ViewModelProvider(
                this,
                new CourseDetailsViewModel.Factory(getApplication(), courseId)
        ).get(CourseDetailsViewModel.class);

        vm.getCourseWStudents().observe(this, cws -> {
            if (cws != null) {
                tvCode.setText(cws.course.getCourseCode());
                tvName.setText(cws.course.getCourseName());
                tvLecturer.setText(cws.course.getLecturerName());
                adapter.submitList(cws.students);
            }
        });
    }
}
