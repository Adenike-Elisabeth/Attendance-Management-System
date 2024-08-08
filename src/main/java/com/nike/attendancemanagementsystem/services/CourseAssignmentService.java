package com.nike.attendancemanagementsystem.services;
public interface CourseAssignmentService {
    void assignCourseToTeacherByAdmin(Long adminId, Long teacherId, Long courseId);

    void assignCourseToStudentByAdmin(Long adminId, Long studentId, Long courseId);

    void assignCourseToStudentByStudent(Long studentId, Long courseId);

}
