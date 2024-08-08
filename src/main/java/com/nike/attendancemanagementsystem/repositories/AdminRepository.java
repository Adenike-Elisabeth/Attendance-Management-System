package com.nike.attendancemanagementsystem.repositories;

import com.nike.attendancemanagementsystem.models.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Example method to check if the ID belongs to an admin
    boolean existsById(Long id);

}
