package com.example.part2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.example.part2.domain.Enrollment;

@Dao
public interface EnrollmentDao {

    @Insert
    void insert(Enrollment e);


    @Query("SELECT COUNT(*) FROM Enrollment WHERE courseId = :cid AND studentId = :sid")
    int countFor(int cid, int sid);


    @Query("DELETE FROM Enrollment WHERE courseId = :cid AND studentId = :sid")
    void remove(int cid, int sid);
}
