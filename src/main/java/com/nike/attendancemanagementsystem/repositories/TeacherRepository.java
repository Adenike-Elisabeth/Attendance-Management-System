package com.nike.attendancemanagementsystem.repositories;

import com.nike.attendancemanagementsystem.models.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // Check if a teacher with a given ID is teaching a specific course
    @Query("SELECT COUNT(t) > 0 FROM Teacher t JOIN t.courses c WHERE t.id = :teacherId AND c.id = :courseId")
    boolean isTeacherAssignedToCourse(Long teacherId, Long courseId);

    @Query("SELECT COUNT(t) > 0 FROM Teacher t JOIN t.courses c WHERE t.id = :teacherId AND c.id = :courseId")
    boolean existsByIdAndCoursesId(Long teacherId, Long courseId);

}
