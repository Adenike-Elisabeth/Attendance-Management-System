package com.nike.attendancemanagementsystem.controllers;

import com.nike.attendancemanagementsystem.services.CourseAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course-assignments")
public class CourseAssignmentController {

    private final CourseAssignmentService courseAssignmentService;

    @PostMapping("/admin/{adminId}/students/{studentId}/courses/{courseId}")
    public void assignCourseToStudentByAdmin(@PathVariable Long adminId, @PathVariable Long studentId, @PathVariable Long courseId) {
        courseAssignmentService.assignCourseToStudentByAdmin(adminId, studentId, courseId);
    }

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public void assignCourseToStudentByStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        courseAssignmentService.assignCourseToStudentByStudent(studentId, courseId);
    }

    @PostMapping("/admin/{adminId}/teachers/{teacherId}/courses/{courseId}")
    public void assignCourseToTeacherByAdmin(@PathVariable Long adminId, @PathVariable Long teacherId, @PathVariable Long courseId) {
        courseAssignmentService.assignCourseToTeacherByAdmin(adminId, teacherId, courseId);
    }

}
