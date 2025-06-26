package com.example.hotel_reservation_system.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
public class Booking {
    private User user;
    private Room room;
    private Date checkIn;
    private Date checkOut;

    public int getNumberOfNights() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "User " + user.getUserId() + " booked Room " + room.getRoomNumber() +
                " (" + room.getRoomType() + ") from " + sdf.format(checkIn) +
                " to " + sdf.format(checkOut);
    }
}