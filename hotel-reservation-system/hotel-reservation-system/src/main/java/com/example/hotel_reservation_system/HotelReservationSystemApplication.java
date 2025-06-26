package com.example.hotel_reservation_system;

import com.example.hotel_reservation_system.Model.RoomType;
import com.example.hotel_reservation_system.Service.BookingService;
import com.example.hotel_reservation_system.Service.RoomService;
import com.example.hotel_reservation_system.Service.UserService;

import java.text.SimpleDateFormat;

public class HotelReservationSystemApplication {
	public static void main(String[] args) throws Exception {
		RoomService roomService = new RoomService();
		UserService userService = new UserService();
		BookingService bookingService = new BookingService(roomService, userService);

		// Rooms
		roomService.setRoom(1, RoomType.STANDARD, 1000);
		roomService.setRoom(2, RoomType.JUNIOR_SUITE, 2000);
		roomService.setRoom(3, RoomType.MASTER_SUITE, 3000);

		// Users
		userService.setUser(1, 5000);
		userService.setUser(2, 10000);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			bookingService.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026"));  // Valid
		} catch (Exception e) {
			System.out.println("Booking error 1: " + e.getMessage());
		}

		try {
			bookingService.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026"));  // Invalid dates
		} catch (Exception e) {
			System.out.println("Booking error 2 (dates reversed): " + e.getMessage());
		}

		try {
			bookingService.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));  // Valid
		} catch (Exception e) {
			System.out.println("Booking error 3: " + e.getMessage());
		}

		try {
			bookingService.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026"));  // Valid
		} catch (Exception e) {
			System.out.println("Booking error 4: " + e.getMessage());
		}

		try {
			bookingService.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));  // Valid
		} catch (Exception e) {
			System.out.println("Booking error 5: " + e.getMessage());
		}

		roomService.setRoom(1, RoomType.MASTER_SUITE, 10000);

		System.out.println("\n=== All Users ===");
		userService.printAllUsers();

		System.out.println("\n=== All Rooms ===");
		roomService.printAllRooms();

		System.out.println("\n=== All Bookings ===");
		bookingService.printAllBookings();
	}
}
