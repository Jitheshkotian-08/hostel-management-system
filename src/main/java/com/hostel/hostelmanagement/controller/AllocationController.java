package com.hostel.hostelmanagement.controller;

import com.hostel.hostelmanagement.entity.*;
import com.hostel.hostelmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/allocate")
public class AllocationController {

    @Autowired
    private AllocationRepository allocationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping
    public String allocateRoom(@RequestParam Long studentId,
                               @RequestParam Long roomId) {

        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Room> roomOpt = roomRepository.findById(roomId);

        if (studentOpt.isEmpty()) {
            return "Student not found ❌";
        }

        if (roomOpt.isEmpty()) {
            return "Room not found ❌";
        }

        Room room = roomOpt.get();

        if (room.getOccupied() >= room.getCapacity()) {
            return "Room is full ❌";
        }

        // Allocate
        Allocation allocation = new Allocation();
        allocation.setStudentId(studentId);
        allocation.setRoomId(roomId);
        allocation.setAllocationDate(LocalDate.now());

        allocationRepository.save(allocation);

        // Increase occupied count
        room.setOccupied(room.getOccupied() + 1);
        roomRepository.save(room);

        return "Room allocated successfully ✅";
    }
}