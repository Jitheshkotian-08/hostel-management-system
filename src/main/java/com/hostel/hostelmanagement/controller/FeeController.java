package com.hostel.hostelmanagement.controller;

import com.hostel.hostelmanagement.entity.Fee;
import com.hostel.hostelmanagement.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class FeeController {

    @Autowired
    private FeeRepository feeRepository;

    // Add fee
    @PostMapping
    public Fee addFee(@RequestBody Fee fee) {
        return feeRepository.save(fee);
    }

    // Get pending fees
    @GetMapping("/pending")
    public List<Fee> getPendingFees() {
        return feeRepository.findByStatus("PENDING");
    }

    @GetMapping
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }
}
