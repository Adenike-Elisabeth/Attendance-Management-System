package com.nike.attendancemanagementsystem.services.impl;

import com.nike.attendancemanagementsystem.models.dtos.request.TeacherAttendanceRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.TeacherAttendanceResponse;
import com.nike.attendancemanagementsystem.models.entities.TeacherAttendance;
import com.nike.attendancemanagementsystem.repositories.AdminRepository;
import com.nike.attendancemanagementsystem.repositories.CourseRepository;
import com.nike.attendancemanagementsystem.repositories.TeacherAttendanceRepository;
import com.nike.attendancemanagementsystem.repositories.TeacherRepository;
import com.nike.attendancemanagementsystem.services.TeacherAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {

    private final TeacherAttendanceRepository teacherAttendanceRepository;

    private final TeacherRepository teacherRepository;

    private final AdminRepository adminRepository;

    private final CourseRepository courseRepository;

    @Override
    public TeacherAttendanceResponse recordAttendance(TeacherAttendanceRequest request) {
        if (!adminRepository.existsById(request.getAdminId())) {
            throw new SecurityException("Unauthorized: Only admins can record teacher attendance.");
        }

        TeacherAttendance attendance = TeacherAttendance.builder()
                .teacher(teacherRepository.findById(request.getTeacherId())
                        .orElseThrow(() -> new RuntimeException("Teacher not found")))
                .course(courseRepository.findById(request.getCourseId())
                        .orElseThrow(() -> new RuntimeException("Course not found")))
                .timestamp(request.getTimestamp())
                .isPresent(request.isPresent())
                .isAbsent(request.isAbsent())
                .admin(adminRepository.findById(request.getAdminId())
                        .orElseThrow(() -> new RuntimeException("Admin not found")))
                .build();


        attendance = teacherAttendanceRepository.save(attendance);

        return new TeacherAttendanceResponse(attendance.getId(), attendance.getTeacher().getId(),
                attendance.getCourse().getId(), attendance.getTimestamp(), attendance.isPresent(), attendance.isAbsent(), attendance.getAdmin().getId());
    }

    @Override
    public TeacherAttendanceResponse getTeacherAttendance(Long id) {
        TeacherAttendance attendance = teacherAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher Attendance not found"));
        return new TeacherAttendanceResponse(attendance.getId(), attendance.getTeacher().getId(),
                attendance.getCourse().getId(), attendance.getTimestamp(),
                attendance.isPresent(), attendance.isAbsent(), attendance.getAdmin().getId());
    }


    @Override
    public List<TeacherAttendanceResponse> getAllTeacherAttendances() {
        List<TeacherAttendance> attendances = teacherAttendanceRepository.findAll();
        return attendances.stream().map(attendance -> new TeacherAttendanceResponse(attendance.getId(),
                        attendance.getTeacher().getId(), attendance.getCourse().getId(), attendance.getTimestamp(),
                        attendance.isPresent(), attendance.isAbsent(), attendance.getAdmin().getId()))
                .collect(Collectors.toList());
    }

@Override
    public TeacherAttendanceResponse updateTeacherAttendance(Long id, TeacherAttendanceRequest request) {
        TeacherAttendance attendance = teacherAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher Attendance not found"));

        if (!adminRepository.existsById(request.getAdminId())) {
            throw new SecurityException("Unauthorized: Only admins can update teacher attendance.");
        }

        attendance.setTeacher(teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found")));
        attendance.setCourse(courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found")));
        attendance.setTimestamp(request.getTimestamp());
        attendance.setPresent(request.isPresent());
        attendance.setAbsent(request.isAbsent());
        attendance.setAdmin(adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found")));

        teacherAttendanceRepository.save(attendance);

        return new TeacherAttendanceResponse(attendance.getId(), attendance.getTeacher().getId(),
                attendance.getCourse().getId(), attendance.getTimestamp(),
                attendance.isPresent(), attendance.isAbsent(), attendance.getAdmin().getId());
    }

@Override
    public void deleteTeacherAttendance(Long id) {
        if (!teacherAttendanceRepository.existsById(id)) {
            throw new RuntimeException("Teacher Attendance not found");
        }
        teacherAttendanceRepository.deleteById(id);
    }

}
