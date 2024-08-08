package com.nike.attendancemanagementsystem.repositories;

import com.nike.attendancemanagementsystem.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
