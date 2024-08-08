package com.nike.attendancemanagementsystem.services;

import com.nike.attendancemanagementsystem.models.dtos.request.CourseRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourseByAdmin(Long adminId, CourseRequest courseRequest);
    CourseResponse getCourse(Long courseId);

    List<CourseResponse> getAllCourses();

    void deleteCourse(Long courseId);

    CourseResponse updateCourse(Long courseId, CourseRequest request);
}
