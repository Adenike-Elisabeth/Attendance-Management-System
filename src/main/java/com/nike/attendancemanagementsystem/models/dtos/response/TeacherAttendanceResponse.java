package com.nike.attendancemanagementsystem.models.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAttendanceResponse {
    private Long id;

    private Long teacherId;

    private Long courseId;

    private LocalDateTime timestamp = LocalDateTime.now(); // Initialize with current date and time

    @JsonProperty("isPresent")
    private boolean isPresent;

    @JsonProperty("isAbsent")
    private boolean isAbsent;

    private Long adminId;  // Admin who recorded the attendance

}
