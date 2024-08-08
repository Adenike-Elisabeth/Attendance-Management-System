package com.nike.attendancemanagementsystem.services;

import com.nike.attendancemanagementsystem.models.dtos.request.TeacherAttendanceRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.TeacherAttendanceResponse;

import java.util.List;

public interface TeacherAttendanceService {
    TeacherAttendanceResponse recordAttendance(TeacherAttendanceRequest request);

    TeacherAttendanceResponse getTeacherAttendance(Long id);

    List<TeacherAttendanceResponse> getAllTeacherAttendances();

    TeacherAttendanceResponse updateTeacherAttendance(Long id, TeacherAttendanceRequest request);

    void deleteTeacherAttendance(Long id);
}
