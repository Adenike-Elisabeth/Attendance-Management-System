package com.nike.attendancemanagementsystem.controllers;

import com.nike.attendancemanagementsystem.models.dtos.request.AdminRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.request.CourseRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.AdminResponseDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.CourseResponse;
import com.nike.attendancemanagementsystem.models.entities.Admin;
import com.nike.attendancemanagementsystem.services.AdminService;
import com.nike.attendancemanagementsystem.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/create-admin")
    public AdminResponseDTO createAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        return adminService.createAdmin(adminRequestDTO);
    }

    @PutMapping("/update-admin/{id}")
    public AdminResponseDTO updateAdmin(@PathVariable Long id, @RequestBody AdminRequestDTO adminRequestDTO) {
        return adminService.updateAdmin(id, adminRequestDTO);
    }

    @DeleteMapping("/delete-admin/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping("/get-admin/{id}")
    public AdminResponseDTO getAdmin(@PathVariable Long id) {
        return adminService.getAdmin(id);
    }

    @GetMapping("/get-all-admins")
    public List<AdminResponseDTO> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/{adminId}/assign-course-to-teacher/{teacherId}/{courseId}")
    public void assignCourseToTeacher(@PathVariable Long adminId, @PathVariable Long teacherId, @PathVariable Long courseId) {
        adminService.assignCourseToTeacher(adminId, teacherId, courseId);
    }

    @PostMapping("/{adminId}/assign-course-to-student/{studentId}/{courseId}")
    public void assignCourseToStudent(@PathVariable Long adminId, @PathVariable Long studentId, @PathVariable Long courseId) {
        adminService.assignCourseToStudent(adminId, studentId, courseId);
    }

}
