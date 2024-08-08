package com.nike.attendancemanagementsystem.services.impl;

import com.nike.attendancemanagementsystem.models.dtos.request.StudentAttendanceRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.StudentAttendanceResponse;
import com.nike.attendancemanagementsystem.models.entities.StudentAttendance;
import com.nike.attendancemanagementsystem.repositories.*;
import com.nike.attendancemanagementsystem.services.StudentAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentAttendanceServiceImpl implements StudentAttendanceService {


    private final StudentAttendanceRepository studentAttendanceRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    private final AdminRepository adminRepository;


    public StudentAttendanceResponse recordAttendance(StudentAttendanceRequest request) {
        // Validate that the teacher is assigned to the course
        if (!teacherRepository.existsByIdAndCoursesId(request.getTeacherId(), request.getCourseId())) {
            throw new SecurityException("Unauthorized: This teacher is not assigned to the specified course.");
        }

        // Create and save the attendance record
        StudentAttendance attendance = StudentAttendance.builder()
                .student(studentRepository.findById(request.getStudentId())
                        .orElseThrow(() -> new RuntimeException("Student not found")))
                .course(courseRepository.findById(request.getCourseId())
                        .orElseThrow(() -> new RuntimeException("Course not found")))
                .timestamp(request.getTimestamp())
                .isPresent(request.isPresent())
                .isAbsent(request.isAbsent())
                .teacher(teacherRepository.findById(request.getTeacherId())
                        .orElseThrow(() -> new RuntimeException("Teacher not found")))
                .build();

        attendance = studentAttendanceRepository.save(attendance);

        return new StudentAttendanceResponse(attendance.getId(), attendance.getStudent().getId(),
                attendance.getCourse().getId(), attendance.getTimestamp(),
           attendance.isPresent(), attendance.isAbsent(), attendance.getTeacher().getId());
    }

    @Override
    public StudentAttendanceResponse getStudentAttendance(Long id) {
        StudentAttendance attendance = studentAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        return new StudentAttendanceResponse(attendance.getId(), attendance.getStudent().getId(),
                attendance.getCourse().getId(), attendance.getTimestamp(),
                attendance.isPresent(), attendance.isAbsent(), attendance.getTeacher().getId());
    }

    @Override
    public List<StudentAttendanceResponse> getAllStudentAttendances() {
        List<StudentAttendance> attendances = studentAttendanceRepository.findAll();
        return attendances.stream().map(attendance -> new StudentAttendanceResponse(attendance.getId(),
                        attendance.getStudent().getId(), attendance.getCourse().getId(), attendance.getTimestamp(),
                        attendance.isPresent(), attendance.isAbsent(), attendance.getTeacher().getId()))
                .collect(Collectors.toList());
    }


    @Override
    public StudentAttendanceResponse updateStudentAttendance(Long id, StudentAttendanceRequest request) {
        StudentAttendance attendance = studentAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        // Optional: Check if the teacher is still assigned to the course
        if (!teacherRepository.existsByIdAndCoursesId(request.getTeacherId(), request.getCourseId())) {
            throw new SecurityException("Unauthorized: This teacher is not assigned to the specified course.");
        }

        attendance.setStudent(studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found")));
        attendance.setCourse(courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found")));
        attendance.setTimestamp(request.getTimestamp());
        attendance.setPresent(request.isPresent());
        attendance.setAbsent(request.isAbsent());
        attendance.setTeacher(teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found")));

        studentAttendanceRepository.save(attendance);

        return new StudentAttendanceResponse(attendance.getId(), attendance.getStudent().getId(),
                attendance.getCourse().getId(), attendance.getTimestamp(),
                attendance.isPresent(), attendance.isAbsent(), attendance.getTeacher().getId());
    }

@Override
    public void deleteStudentAttendance(Long id) {
        if (!studentAttendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found");
        }
        studentAttendanceRepository.deleteById(id);
    }


}
