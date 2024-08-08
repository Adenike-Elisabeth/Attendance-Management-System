package com.nike.attendancemanagementsystem.services;

import com.nike.attendancemanagementsystem.models.dtos.request.StudentAttendanceRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.StudentAttendanceResponse;

import java.util.List;

public interface StudentAttendanceService {
    StudentAttendanceResponse recordAttendance(StudentAttendanceRequest request);

    StudentAttendanceResponse getStudentAttendance(Long id);

    List<StudentAttendanceResponse> getAllStudentAttendances();

    StudentAttendanceResponse updateStudentAttendance(Long id, StudentAttendanceRequest request);

    void deleteStudentAttendance(Long id);
}
