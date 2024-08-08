package com.nike.attendancemanagementsystem.repositories;

import com.nike.attendancemanagementsystem.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
