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
import com.example.part2.dao.EnrollmentDao;
import com.example.part2.db.AppDatabase;
import com.example.part2.domain.Enrollment;
import com.example.part2.domain.Student;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class StudentListAdapter
        extends ListAdapter<Student, StudentListAdapter.StudentViewHolder> {

    private final int courseId;

    public StudentListAdapter(int courseId) {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(
                    @NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getStudentId() == newItem.getStudentId();
            }

            @Override
            public boolean areContentsTheSame(
                    @NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getName().equals(newItem.getName())
                        && oldItem.getEmail().equals(newItem.getEmail())
                        && oldItem.getUserName().equals(newItem.getUserName());
            }
        });
        this.courseId = courseId;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull StudentViewHolder holder, int position) {
        Student student = getItem(position);

        holder.tvName.setText(student.getName());
        holder.tvEmail.setText(student.getEmail());
        holder.tvUsername.setText(student.getUserName());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), StudentDetailsActivity.class);
            i.putExtra(
                    StudentDetailsActivity.EXTRA_STUDENT_ID,
                    student.getStudentId()
            );
            v.getContext().startActivity(i);
        });

        holder.itemView.setOnLongClickListener(v -> {
            new MaterialAlertDialogBuilder(v.getContext())
                    .setTitle("Student Options")
                    .setItems(new String[]{"Edit","Remove"}, (dialog, which) -> {
                        if (which == 0) {
                            Intent i = new Intent(v.getContext(), EditStudentActivity.class);
                            i.putExtra(
                                    EditStudentActivity.EXTRA_STUDENT_ID,
                                    student.getStudentId()
                            );
                            i.putExtra(
                                    EditStudentActivity.EXTRA_COURSE_ID,
                                    courseId
                            );
                            v.getContext().startActivity(i);
                        } else {
                            new Thread(() -> {
                                EnrollmentDao ed = AppDatabase
                                        .getInstance(v.getContext())
                                        .enrollmentDao();
                                ed.remove(courseId, student.getStudentId());
                            }).start();
                        }
                    })
                    .show();
            return true;
        });
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName, tvEmail, tvUsername;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName     = itemView.findViewById(R.id.tvStudentName);
            tvEmail    = itemView.findViewById(R.id.tvStudentEmail);
            tvUsername = itemView.findViewById(R.id.tvStudentUsername);
        }
    }
}
