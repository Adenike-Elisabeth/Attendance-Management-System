package com.nike.attendancemanagementsystem.controllers;

import com.nike.attendancemanagementsystem.models.dtos.request.TeacherAttendanceRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.TeacherAttendanceResponse;
import com.nike.attendancemanagementsystem.services.TeacherAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher-attendance")
public class TeacherAttendanceController {

    private final TeacherAttendanceService teacherAttendanceService;


    @PostMapping("/record-teacher-attendance")
    public ResponseEntity<TeacherAttendanceResponse> recordAttendance(@RequestBody TeacherAttendanceRequest request) {
        TeacherAttendanceResponse response = teacherAttendanceService.recordAttendance(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-teacher-attendance/{id}")
    public ResponseEntity<TeacherAttendanceResponse> getAttendance(@PathVariable Long id) {
        TeacherAttendanceResponse response = teacherAttendanceService.getTeacherAttendance(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-teacher-attendance")
    public ResponseEntity<List<TeacherAttendanceResponse>> getAllAttendances() {
        List<TeacherAttendanceResponse> responses = teacherAttendanceService.getAllTeacherAttendances();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update-teacher-attendance/{id}")
    public ResponseEntity<TeacherAttendanceResponse> updateAttendance(@PathVariable Long id, @RequestBody TeacherAttendanceRequest request) {
        TeacherAttendanceResponse response = teacherAttendanceService.updateTeacherAttendance(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-teacher-attendance/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        teacherAttendanceService.deleteTeacherAttendance(id);
        return ResponseEntity.ok().build();
    }


}
