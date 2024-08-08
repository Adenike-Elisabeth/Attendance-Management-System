package com.nike.attendancemanagementsystem.repositories;

import com.nike.attendancemanagementsystem.models.entities.TeacherAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherAttendanceRepository extends JpaRepository<TeacherAttendance, Long> {
    List<TeacherAttendance> findByCourseId(Long courseId);
}
