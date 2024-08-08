package com.nike.attendancemanagementsystem.services.impl;

import com.nike.attendancemanagementsystem.models.entities.Course;
import com.nike.attendancemanagementsystem.models.entities.Student;
import com.nike.attendancemanagementsystem.models.entities.Teacher;
import com.nike.attendancemanagementsystem.repositories.CourseRepository;
import com.nike.attendancemanagementsystem.repositories.StudentRepository;
import com.nike.attendancemanagementsystem.repositories.TeacherRepository;
import com.nike.attendancemanagementsystem.services.CourseAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseAssignmentServiceImpl implements CourseAssignmentService {


    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    @Override
    public void assignCourseToTeacherByAdmin(Long adminId, Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    @Override
    public void assignCourseToStudentByAdmin(Long adminId, Long studentId, Long courseId) {
        // Admin validation logic can be added here, e.g., checking if the adminId is valid
        assignCourseToStudent(studentId, courseId);
    }

    @Override
    public void assignCourseToStudentByStudent(Long studentId, Long courseId) {
        // Check if the student is enrolling themselves
        assignCourseToStudent(studentId, courseId);
    }

    private void assignCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        studentRepository.save(student);
    }

}
