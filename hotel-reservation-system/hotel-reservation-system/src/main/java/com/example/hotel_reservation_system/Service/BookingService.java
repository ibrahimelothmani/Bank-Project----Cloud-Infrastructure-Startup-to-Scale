package com.example.hotel_reservation_system.Service;


import com.example.hotel_reservation_system.Model.Booking;
import com.example.hotel_reservation_system.Model.Room;
import com.example.hotel_reservation_system.Model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();

    private final RoomService roomService;
    private final UserService userService;

    public BookingService(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    private boolean isRoomAvailable(Room room, Date checkIn, Date checkOut) {
        for (Booking b : bookings) {
            if (b.getRoom().getRoomNumber() == room.getRoomNumber()) {
                // Check for overlapping dates
                if (!(checkOut.before(b.getCheckIn()) || checkIn.after(b.getCheckOut()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        User user = userService.findUser(userId);
        if (user == null) throw new IllegalArgumentException("User not found");

        Room room = roomService.findRoom(roomNumber);
        if (room == null) throw new IllegalArgumentException("Room not found");

        if (checkIn.after(checkOut)) throw new IllegalArgumentException("Invalid dates");

        if (!isRoomAvailable(room, checkIn, checkOut)) throw new IllegalStateException("Room not available");

        Booking booking = new Booking(user, room, checkIn, checkOut);
        int nights = booking.getNumberOfNights();
        int totalPrice = nights * room.getPricePerNight();

        if (user.getBalance() < totalPrice) throw new IllegalStateException("Insufficient balance");

        user.setBalance(user.getBalance() - totalPrice);
        bookings.add(booking);
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public void printAllBookings() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Bookings:");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            Booking b = bookings.get(i);
            System.out.printf("User %d booked Room %d (%s) from %s to %s\n",
                    b.getUser().getUserId(),
                    b.getRoom().getRoomNumber(),
                    b.getRoom().getRoomType(),
                    sdf.format(b.getCheckIn()),
                    sdf.format(b.getCheckOut()));
        }
    }
}
