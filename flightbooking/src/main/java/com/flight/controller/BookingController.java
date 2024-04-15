package com.flight.controller;

import com.flight.database.BookingDB;
import com.flight.domain.Booking;
import com.flight.domain.Flight;
import com.flight.domain.Passenger;
import com.flight.domain.User;
import java.util.List;

/**
 * Controller class for managing Booking objects within a flight booking system.
 * Provides methods to create, retrieve, update, and delete bookings,
 * as well as to retrieve all bookings from the database.
 */
public class BookingController {
    private BookingDB bookingDB;

    /**
     * Constructs a BookingController with a new instance of BookingDB.
     */
    public BookingController() {
        this.bookingDB = new BookingDB();
    }

    /**
     * Creates a booking and adds it to the database.
     *
     * @param bookingNo The unique booking number.
     * @param user The user who made the booking.
     * @param flights A list of flights included in the booking.
     * @param passengers A list of passengers included in the booking.
     * @return The newly created Booking object.
     */
    public Booking createBooking(String bookingNo, User user, List<Flight> flights, List<Passenger> passengers) {
        Booking booking = new Booking(bookingNo, user, flights, passengers);
        bookingDB.insert(booking);
        return booking;
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
