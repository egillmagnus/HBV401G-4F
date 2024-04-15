package com.flight.database;

import com.flight.domain.Booking;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates a database interface for managing bookings within a flight reservation system.
 * Provides methods to insert, retrieve, update, and delete bookings, as well as to list all bookings.
 */
public class BookingDB {
    private List<Booking> bookings;

    /**
     * Constructs a new instance of the BookingDB.
     * Initializes an empty list of bookings.
     */
    public BookingDB() {
        this.bookings = new ArrayList<>();
    }

    /**
     * Inserts a new booking into the database if it does not already exist based on the booking number.
     *
     * @param booking The Booking object to be added to the database.
     */
    public void insert(Booking booking) {
        if (getByBookingNo(booking.getBookingNo()) == null) {
            bookings.add(booking);
        }
    }

    /**
     * Retrieves a booking from the database using its unique booking number.
     *
     * @param bookingNo The booking number of the booking to retrieve.
     * @return The Booking object if found; null otherwise.
     */
    public Booking getByBookingNo(String bookingNo) {
        for (Booking booking : bookings) {
            if (booking.getBookingNo().equals(bookingNo)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Updates the details of an existing booking in the database.
     * The method only updates if the booking already exists.
     *
     * @param booking The Booking object containing the updated details.
     */
    public void update(Booking booking) {
        Booking existingBooking = getByBookingNo(booking.getBookingNo());
        if (existingBooking != null) {
            existingBooking.setFlights(booking.getFlights());
            existingBooking.setPassengers(booking.getPassengers());
            existingBooking.setUser(booking.getUser());
        }
    }

    /**
     * Deletes a booking from the database using its unique booking number.
     *
     * @param bookingNo The booking number of the booking to be deleted.
     */
    public void delete(String bookingNo) {
        bookings.removeIf(b -> b.getBookingNo().equals(bookingNo));
    }

    /**
     * Retrieves all bookings currently stored in the database.
     *
     * @return A list of all Booking objects.
     */
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
