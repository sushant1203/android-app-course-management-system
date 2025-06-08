package com.example.part2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.part2.dao.CourseDao;
import com.example.part2.dao.EnrollmentDao;
import com.example.part2.dao.StudentDao;
import com.example.part2.domain.Course;
import com.example.part2.domain.Enrollment;
import com.example.part2.domain.Student;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = { Course.class, Student.class, Enrollment.class },
        version  = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract StudentDao studentDao();
    public abstract EnrollmentDao enrollmentDao();


    private static volatile AppDatabase INSTANCE;


    private static final int THREAD_COUNT = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(THREAD_COUNT);


    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
