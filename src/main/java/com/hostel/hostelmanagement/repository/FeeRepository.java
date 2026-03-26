package com.hostel.hostelmanagement.repository;

import com.hostel.hostelmanagement.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    List<Fee> findByStatus(String status);
}