package com.example.hotel_reservation_system.Service;

import com.example.hotel_reservation_system.Model.Booking;
import com.example.hotel_reservation_system.Model.Room;
import com.example.hotel_reservation_system.Model.RoomType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomService {
    private final List<Room> rooms = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int pricePerNight) {
        Room existingRoom = findRoom(roomNumber);
        if (existingRoom == null) {
            rooms.add(new Room(roomNumber, roomType, pricePerNight));
        }
    }

    public Room findRoom(int roomNumber) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public void printAllRooms() {
        System.out.println("Rooms:");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            Room r = rooms.get(i);
            System.out.printf("Room %d: %s, Price per night: %d\n",
                    r.getRoomNumber(), r.getRoomType(), r.getPricePerNight());
        }
    }
}