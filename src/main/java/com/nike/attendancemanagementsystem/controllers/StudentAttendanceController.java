package com.nike.attendancemanagementsystem.controllers;

import com.nike.attendancemanagementsystem.models.dtos.request.StudentAttendanceRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.StudentAttendanceResponse;
import com.nike.attendancemanagementsystem.services.StudentAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student-attendance")
public class StudentAttendanceController {

    private final StudentAttendanceService studentAttendanceService;


    @PostMapping("/mark-student-attendance")
    public ResponseEntity<StudentAttendanceResponse> markAttendance(@RequestBody StudentAttendanceRequest request) {
        StudentAttendanceResponse response = studentAttendanceService.recordAttendance(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-student-attendance/{id}")
    public ResponseEntity<StudentAttendanceResponse> getAttendance(@PathVariable Long id) {
        StudentAttendanceResponse response = studentAttendanceService.getStudentAttendance(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-student-attendance")
    public ResponseEntity<List<StudentAttendanceResponse>> getAllAttendances() {
        List<StudentAttendanceResponse> responses = studentAttendanceService.getAllStudentAttendances();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update-student-attendance/{id}")
    public ResponseEntity<StudentAttendanceResponse> updateAttendance(@PathVariable Long id, @RequestBody StudentAttendanceRequest request) {
        StudentAttendanceResponse response = studentAttendanceService.updateStudentAttendance(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-student-attendance/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        studentAttendanceService.deleteStudentAttendance(id);
        return ResponseEntity.ok("Student attendance deleted successfully");
    }

}
