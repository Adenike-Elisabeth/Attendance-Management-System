package com.nike.attendancemanagementsystem.services.impl;

import com.nike.attendancemanagementsystem.models.dtos.request.CourseRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.CourseResponse;
import com.nike.attendancemanagementsystem.models.entities.*;
import com.nike.attendancemanagementsystem.repositories.*;
import com.nike.attendancemanagementsystem.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {


    private final CourseRepository courseRepository;


    private final TeacherRepository teacherRepository;


    private final AdminRepository adminRepository;

    private final StudentAttendanceRepository studentAttendanceRepository;

    private final TeacherAttendanceRepository teacherAttendanceRepository;

    @Override
    public CourseResponse createCourseByAdmin(Long adminId, CourseRequest courseRequest) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Course course = Course.builder()
                .name(courseRequest.getName())
                .teacher(teacherRepository.findById(courseRequest.getTeacherId())
                        .orElseThrow(() -> new RuntimeException("Teacher not found")))
                .build();
        course = courseRepository.save(course);
        return new CourseResponse(course.getId(), course.getName(), course.getTeacher().getId());
    }

    @Override
    public CourseResponse getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return new CourseResponse(course.getId(), course.getName(), course.getTeacher().getId());
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> new CourseResponse(course.getId(), course.getName(), course.getTeacher().getId()))
                .collect(Collectors.toList());
    }

//    @Override
//    public void deleteCourse(Long courseId) {
//        if (!courseRepository.existsById(courseId)) {
//            throw new RuntimeException("Course not found");
//        }
//        courseRepository.deleteById(courseId);
//    }

    @Transactional
    public void deleteCourse(Long courseId) {
        // First, fetch and delete all related attendance records to avoid foreign key constraint violations.
        List<StudentAttendance> attendances = studentAttendanceRepository.findByCourseId(courseId);
        if (attendances != null && !attendances.isEmpty()) {
            studentAttendanceRepository.deleteAll(attendances);
        }

        // Fetch and delete all related teacher attendance records first
        List<TeacherAttendance> teacherAttendances = teacherAttendanceRepository.findByCourseId(courseId);
        if (!teacherAttendances.isEmpty()) {
            teacherAttendanceRepository.deleteAll(teacherAttendances);
        }

        // Now it's safe to delete the course
        courseRepository.deleteById(courseId);
    }


//    @Transactional
//    public void deleteCourse(Long courseId) {
//        // Fetch and delete all related teacher attendance records first
//        List<TeacherAttendance> teacherAttendances = teacherAttendanceRepository.findByCourseId(courseId);
//        if (!teacherAttendances.isEmpty()) {
//            teacherAttendanceRepository.deleteAll(teacherAttendances);
//        }
//
//        // Now safe to delete the course
//        courseRepository.deleteById(courseId);
//    }



    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setName(request.getName());
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        course.setTeacher(teacher);

        course = courseRepository.save(course);
        return new CourseResponse(course.getId(), course.getName(), course.getTeacher().getId());
    }


}
