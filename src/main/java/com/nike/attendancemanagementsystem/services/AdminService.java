package com.nike.attendancemanagementsystem.services;

import com.nike.attendancemanagementsystem.models.dtos.request.AdminRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.AdminResponseDTO;
import java.util.List;

public interface AdminService {
    AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO);

    AdminResponseDTO updateAdmin(Long id, AdminRequestDTO adminRequestDTO);

    void deleteAdmin(Long id);

    AdminResponseDTO getAdmin(Long id);

    List<AdminResponseDTO> getAllAdmins();
    void assignCourseToTeacher(Long adminId, Long teacherId, Long courseId);
    void assignCourseToStudent(Long adminId, Long studentId, Long courseId);
}
