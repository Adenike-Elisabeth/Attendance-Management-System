package com.nike.attendancemanagementsystem.models.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttendanceResponse {
    private Long id;
    private Long studentId;
    private Long courseId;

    private LocalDateTime timestamp = LocalDateTime.now(); // Initialize with current date and time


    @JsonProperty("isPresent")
    private boolean isPresent;

    @JsonProperty("isAbsent")
    private boolean isAbsent;

    private Long teacherId;


}
