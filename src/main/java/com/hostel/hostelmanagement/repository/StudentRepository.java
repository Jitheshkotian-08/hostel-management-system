package com.hostel.hostelmanagement.repository;

import com.hostel.hostelmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}