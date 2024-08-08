package com.nike.attendancemanagementsystem.services;


import com.nike.attendancemanagementsystem.models.dtos.request.StudentRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.StudentResponseDTO;
import com.nike.attendancemanagementsystem.models.entities.Student;

import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);
    void deleteStudent(Long id);
    StudentResponseDTO getStudent(Long id);
    List<StudentResponseDTO> getAllStudents();
}
