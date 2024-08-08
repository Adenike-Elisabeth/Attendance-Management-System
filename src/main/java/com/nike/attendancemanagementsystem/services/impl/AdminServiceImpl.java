package com.nike.attendancemanagementsystem.services.impl;

import com.nike.attendancemanagementsystem.models.dtos.request.AdminRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.AdminResponseDTO;
import com.nike.attendancemanagementsystem.models.entities.Admin;
import com.nike.attendancemanagementsystem.models.entities.Course;
import com.nike.attendancemanagementsystem.models.entities.Student;
import com.nike.attendancemanagementsystem.models.entities.Teacher;
import com.nike.attendancemanagementsystem.repositories.AdminRepository;
import com.nike.attendancemanagementsystem.repositories.CourseRepository;
import com.nike.attendancemanagementsystem.repositories.StudentRepository;
import com.nike.attendancemanagementsystem.repositories.TeacherRepository;
import com.nike.attendancemanagementsystem.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;


    private final StudentRepository studentRepository;


    private final TeacherRepository teacherRepository;


    private final CourseRepository courseRepository;


    @Override
    public AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = Admin.builder()
                .name(adminRequestDTO.getName())
                .email(adminRequestDTO.getEmail())
                .build();
        admin = adminRepository.save(admin);
        return mapToResponse(admin);
    }

    @Override
    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO adminRequestDTO) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setName(adminRequestDTO.getName());
        admin.setEmail(adminRequestDTO.getEmail());
        admin = adminRepository.save(admin);
        return mapToResponse(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }


    @Override
    public AdminResponseDTO getAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return mapToResponse(admin);
    }



    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    private AdminResponseDTO mapToResponse(Admin admin) {
        return new AdminResponseDTO(admin.getId(), admin.getName(), admin.getEmail());
    }


    @Override
    public void assignCourseToTeacher(Long adminId, Long teacherId, Long courseId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTeacher(teacher);
        courseRepository.save(course);
    }



    @Override
    public void assignCourseToStudent(Long adminId, Long studentId, Long courseId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        studentRepository.save(student);
    }
}
