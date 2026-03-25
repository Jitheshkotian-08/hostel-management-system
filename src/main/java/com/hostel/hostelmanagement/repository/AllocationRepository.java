package com.hostel.hostelmanagement.repository;

import com.hostel.hostelmanagement.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {
}
