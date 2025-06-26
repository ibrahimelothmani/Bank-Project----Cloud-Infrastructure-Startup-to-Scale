package com.example.hotel_reservation_system.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private int roomNumber;
    private RoomType roomType;
    private int pricePerNight;



}
