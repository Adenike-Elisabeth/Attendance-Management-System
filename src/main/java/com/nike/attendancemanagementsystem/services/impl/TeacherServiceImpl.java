package com.nike.attendancemanagementsystem.services.impl;

import com.nike.attendancemanagementsystem.models.dtos.request.TeacherRequestDTO;
import com.nike.attendancemanagementsystem.models.dtos.response.TeacherResponseDTO;
import com.nike.attendancemanagementsystem.models.entities.Teacher;
import com.nike.attendancemanagementsystem.repositories.TeacherRepository;
import com.nike.attendancemanagementsystem.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = Teacher.builder()
                .name(teacherRequestDTO.getName())
                .email(teacherRequestDTO.getEmail())
                .build();
        teacher = teacherRepository.save(teacher);
        return mapToResponse(teacher);
    }

    @Override
    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacher.setName(teacherRequestDTO.getName());
        teacher.setEmail(teacherRequestDTO.getEmail());
        teacher = teacherRepository.save(teacher);
        return mapToResponse(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherResponseDTO getTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return mapToResponse(teacher);
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private TeacherResponseDTO mapToResponse(Teacher teacher) {
        return new TeacherResponseDTO(teacher.getId(), teacher.getName(), teacher.getEmail());
    }
}
