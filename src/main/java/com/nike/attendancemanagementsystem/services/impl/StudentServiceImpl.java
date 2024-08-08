package com.nike.attendancemanagementsystem.services.impl;

import com.nike.attendancemanagementsystem.models.dtos.request.StudentRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.StudentResponseDTO;
import com.nike.attendancemanagementsystem.models.entities.Student;
import com.nike.attendancemanagementsystem.repositories.StudentRepository;
import com.nike.attendancemanagementsystem.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = Student.builder()
                .name(studentRequestDTO.getName())
                .email(studentRequestDTO.getEmail())
                .build();
        student = studentRepository.save(student);
        return mapToResponse(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student = studentRepository.save(student);
        return mapToResponse(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDTO getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToResponse(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private StudentResponseDTO mapToResponse(Student student) {
        return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail());
    }
}
