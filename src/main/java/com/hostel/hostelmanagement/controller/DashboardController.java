package com.hostel.hostelmanagement.controller;

import com.hostel.hostelmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FeeRepository feeRepository;

    @GetMapping
    public Map<String, Object> getDashboard() {

        Map<String, Object> data = new HashMap<>();

        // 1️⃣ Total students
        data.put("totalStudents", studentRepository.count());

        // 2️⃣ Vacant rooms
        long vacantRooms = roomRepository.findAll()
                .stream()
                .filter(room -> room.getCapacity() > room.getOccupied())
                .count();

        data.put("vacantRooms", vacantRooms);

        // 3️⃣ Pending fees
        long pendingFees = feeRepository.findByStatus("PENDING").size();

        data.put("pendingFees", pendingFees);

        return data;
    }
}