package com.nike.attendancemanagementsystem.controllers;

import com.nike.attendancemanagementsystem.models.dtos.request.StudentRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.StudentResponseDTO;
import com.nike.attendancemanagementsystem.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;


    @PostMapping("/create-student")
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.createStudent(studentRequestDTO);
    }

    @PutMapping("/update-student/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.updateStudent(id, studentRequestDTO);
    }

    @DeleteMapping("/delete- student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/get-student/{id}")
    public StudentResponseDTO getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("get-all-students")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
}
