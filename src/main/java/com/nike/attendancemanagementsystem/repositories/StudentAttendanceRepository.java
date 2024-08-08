package com.nike.attendancemanagementsystem.repositories;

import com.nike.attendancemanagementsystem.models.entities.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
    List<StudentAttendance> findByCourseId(Long courseId);
}
