package com.nike.attendancemanagementsystem.controllers;

import com.nike.attendancemanagementsystem.models.dtos.request.CourseRequest;
import com.nike.attendancemanagementsystem.models.dtos.response.CourseResponse;
import com.nike.attendancemanagementsystem.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;


    @PostMapping("/create-courses/{adminId}")
    public CourseResponse createCourseByAdmin(@PathVariable Long adminId, @RequestBody CourseRequest courseRequest) {
        return courseService.createCourseByAdmin(adminId, courseRequest);
    }


    @GetMapping("get-course/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long courseId) {
        CourseResponse response = courseService.getCourse(courseId);
        return ResponseEntity.ok(response);
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> responses = courseService.getAllCourses();
        return ResponseEntity.ok(responses);
    }

    // Update a course
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequest request) {
        CourseResponse response = courseService.updateCourse(courseId, request);
        return ResponseEntity.ok(response);
    }

    // Delete a course
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course has been deleted successfully");  // Return an HTTP 200 status to indicate successful deletion
    }


}
