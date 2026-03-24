package com.hostel.hostelmanagement.controller;

import com.hostel.hostelmanagement.entity.Room;
import com.hostel.hostelmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // Add room
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // Get all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/vacant")
    public List<Map<String, Object>> getVacantRooms() {
        return roomRepository.findAll()
                .stream()
                .filter(room -> room.getCapacity() > room.getOccupied())
                .map(room -> {
                    Map<String, Object> data = new HashMap<>();
                    data.put("roomNumber", room.getRoomNumber());
                    data.put("vacancy", room.getCapacity() - room.getOccupied());
                    return data;
                })
                .toList();
    }
}