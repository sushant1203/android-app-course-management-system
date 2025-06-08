package com.example.part2.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;
import com.example.part2.domain.Course;
import com.example.part2.ui.CourseDetailsActivity;
import com.example.part2.viewmodel.CourseViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CourseListAdapter
        extends ListAdapter<Course, CourseListAdapter.CourseViewHolder> {

    private final CourseViewModel viewModel;

    public CourseListAdapter(CourseViewModel viewModel) {
        super(new DiffUtil.ItemCallback<Course>() {
            @Override public boolean areItemsTheSame(@NonNull Course a, @NonNull Course b) {
                return a.getCourseId() == b.getCourseId();
            }
            @Override public boolean areContentsTheSame(@NonNull Course a, @NonNull Course b) {
                return a.getCourseCode().equals(b.getCourseCode())
                        && a.getCourseName().equals(b.getCourseName())
                        && a.getLecturerName().equals(b.getLecturerName());
            }
        });
        this.viewModel = viewModel;
    }

    public CourseListAdapter() {
        this(null);
    }

    @NonNull @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder h, int pos) {
        Course c = getItem(pos);
        h.tvCode     .setText(c.getCourseCode());
        h.tvName     .setText(c.getCourseName());
        h.tvLecturer .setText(c.getLecturerName());

        h.itemView.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), CourseDetailsActivity.class);
            i.putExtra(CourseDetailsActivity.EXTRA_COURSE_ID, c.getCourseId());
            v.getContext().startActivity(i);
        });


        if (viewModel != null) {
            h.itemView.setOnLongClickListener(v -> {
                new MaterialAlertDialogBuilder(v.getContext())
                        .setTitle("Delete course?")
                        .setMessage("This will delete the course and all its enrollments. Continue?")
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                        .setPositiveButton("Delete", (dialog, which) -> {
                            viewModel.delete(c);
                        })
                        .show();
                return true;
            });
        }
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        final TextView tvCode, tvName, tvLecturer;
        CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCode     = itemView.findViewById(R.id.tvCourseCode);
            tvName     = itemView.findViewById(R.id.tvCourseName);
            tvLecturer = itemView.findViewById(R.id.tvLecturerName);
        }
    }
}