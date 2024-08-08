package com.nike.attendancemanagementsystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime timestamp;

    private boolean isPresent;

    private boolean isAbsent;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;  // Admin who recorded the attendance

}
