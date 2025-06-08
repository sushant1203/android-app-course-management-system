package com.example.part2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.ui.CreateCourseActivity;
import com.example.part2.ui.CourseListAdapter;
import com.example.part2.viewmodel.CourseViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recyclerCourses);

        courseViewModel = new ViewModelProvider(this)
                .get(CourseViewModel.class);

        CourseListAdapter adapter = new CourseListAdapter(courseViewModel);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        courseViewModel.getAllCourses()
                .observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fabAddCourse);
        fab.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        CreateCourseActivity.class
                ))
        );
    }
}
