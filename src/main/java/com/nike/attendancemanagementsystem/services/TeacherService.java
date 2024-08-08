package com.nike.attendancemanagementsystem.services;

import com.nike.attendancemanagementsystem.models.dtos.request.TeacherRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.TeacherResponseDTO;

import java.util.List;

public interface TeacherService {

    TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO teacherRequestDTO);

    void deleteTeacher(Long id);

    TeacherResponseDTO getTeacher(Long id);

    List<TeacherResponseDTO> getAllTeachers();
}
