package com.nike.attendancemanagementsystem.controllers;

import com.nike.attendancemanagementsystem.models.dtos.request.TeacherRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.TeacherResponseDTO;
import com.nike.attendancemanagementsystem.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/create-teacher")
    public TeacherResponseDTO createTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO) {
        return teacherService.createTeacher(teacherRequestDTO);
    }

    @PutMapping("/update-teacher/{id}")
    public TeacherResponseDTO updateTeacher(@PathVariable Long id, @RequestBody TeacherRequestDTO teacherRequestDTO) {
        return teacherService.updateTeacher(id, teacherRequestDTO);
    }

    @DeleteMapping("/delete-teacher/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @GetMapping("/get-teacher/{id}")
    public TeacherResponseDTO getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @GetMapping("/get-all-teachers")
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
