package com.flight.controller;

import com.flight.database.BookingDB;
import com.flight.domain.Booking;
import com.flight.domain.Flight;
import com.flight.domain.Passenger;
import com.flight.domain.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class for managing Booking objects within a flight booking system.
 * Provides methods to create, retrieve, update, and delete bookings,
 * as well as to retrieve all bookings from the database.
 */
public class BookingController {
    private BookingDB bookingDB;
    private int currentYear;
    private int bookingCount;

    /**
     * Constructs a BookingController with a new instance of BookingDB.
     * Initializes booking counter from a persistent storage.
     */
    public BookingController() {
        this.bookingDB = new BookingDB();
        this.currentYear = LocalDateTime.now().getYear();
        // Initialize bookingCount from database or file
        this.bookingCount = 1;
    }

    /**
     * Creates a booking and adds it to the database.
     * Generates a unique booking number automatically based on the last two digits of the year and an enumerating number.
     *
     * @param user The user who made the booking.
     * @param flights A list of flights included in the booking.
     * @param passengers A list of passengers included in the booking.
     * @return The newly created Booking object.
     */
    public Booking createBooking(User user, List<Flight> flights, List<Passenger> passengers) {
        String bookingNo = generateBookingNo();
        Booking booking = new Booking(bookingNo, user, flights, passengers);
        bookingDB.insert(booking);
        return booking;
    }

    /**
     * Generates a unique booking number based on the current year and a sequence number.
     *
     * @return A unique booking number as a String.
     */
    private String generateBookingNo() {
        int yearPart = LocalDateTime.now().getYear() % 100; // Last two digits of the year
        if (currentYear != LocalDateTime.now().getYear()) {
            currentYear = LocalDateTime.now().getYear();
            bookingCount = 0; // Reset counter if year changes
        }
        return String.format("%02d%06d", yearPart, ++bookingCount);
    }
    /**
     * Retrieves a booking from the database by its booking number.
     *
     * @param bookingNo The booking number to search for.
     * @return The Booking object if found, or null otherwise.
     */
    public Booking getBooking(String bookingNo) {
        return bookingDB.getByBookingNo(bookingNo);
    }

    /**
     * Updates an existing booking in the database.
     *
     * @param bookingNo The booking number of the booking to be updated.
     * @param newUser The updated user information.
     * @param newFlights The updated list of flights.
     * @param newPassengers The updated list of passengers.
     */
    public void updateBooking(String bookingNo, User newUser, List<Flight> newFlights, List<Passenger> newPassengers) {
        Booking booking = new Booking(bookingNo, newUser, newFlights, newPassengers);
        bookingDB.update(booking);
    }

    /**
     * Deletes a booking from the database using its booking number.
     *
     * @param bookingNo The booking number of the booking to be deleted.
     */
    public void deleteBooking(String bookingNo) {
        bookingDB.delete(bookingNo);
    }

    /**
     * Retrieves all bookings currently stored in the database.
     *
     * @return A list of all bookings.
     */
    public List<Booking> getAllBookings() {
        return bookingDB.getAllBookings();
    }
}
