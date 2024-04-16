package com.flight.controller;

import com.flight.domain.Booking;
import com.flight.domain.Flight;
import com.flight.domain.Passenger;
import com.flight.domain.User;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingControllerTest {

    private BookingController bookingController;
    private User user;
    private Flight flight;
    private Passenger passenger;
    private Booking booking;

    @BeforeEach
    void setUp() {
        bookingController = new BookingController(); // Initializes the controller and its in-memory database

        user = new User("John Doe", 1, "johndoe@example.com", 1234567890);
        flight = new Flight("FL100", "New York", "London", LocalDateTime.of(2024, 4, 20, 14, 0), LocalDateTime.of(2024, 4, 20, 18, 0));
        passenger = new Passenger("Jane Doe", "987654321");

        booking = bookingController.createBooking(user, Arrays.asList(flight), Arrays.asList(passenger));
    }

    @Test
    void testCreateBooking() {
        User newUser = new User("Alice Johnson", 2, "alice@example.com", 987654321);
        Flight newFlight = new Flight("FL200", "Tokyo", "Singapore", LocalDateTime.now(), LocalDateTime.now().plusHours(8));
        Passenger newPassenger = new Passenger("Bob Smith", "123456789");

        Booking newBooking = bookingController.createBooking(newUser, Arrays.asList(newFlight), Arrays.asList(newPassenger));

        assertNotNull(newBooking);
        assertEquals(newUser, newBooking.getUser());
        assertTrue(newBooking.getFlights().contains(newFlight));
        assertTrue(newBooking.getPassengers().contains(newPassenger));
    }

    @Test
    void testGetBooking() {
        Booking retrievedBooking = bookingController.getBooking(booking.getBookingNo());
        assertNotNull(retrievedBooking);
        assertEquals("John Doe", retrievedBooking.getUser().getName());
    }

    @Test
    void testUpdateBooking() {
        User updatedUser = new User("Updated User", 3, "updated@example.com", 123123123);
        Flight updatedFlight = new Flight("FL300", "Berlin", "Paris", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Passenger updatedPassenger = new Passenger("Updated Passenger", "321321321");

        bookingController.updateBooking(booking.getBookingNo(), updatedUser, Arrays.asList(updatedFlight), Arrays.asList(updatedPassenger));
        Booking updatedBooking = bookingController.getBooking(booking.getBookingNo());

        assertEquals("Updated User", updatedBooking.getUser().getName());
        assertTrue(updatedBooking.getFlights().contains(updatedFlight));
        assertTrue(updatedBooking.getPassengers().contains(updatedPassenger));
    }

    @Test
    void testDeleteBooking() {
        assertNotNull(bookingController.getBooking(booking.getBookingNo()));
        bookingController.deleteBooking(booking.getBookingNo());
        assertNull(bookingController.getBooking(booking.getBookingNo()));
    }

    @Test
    void testGetAllBookings() {
        // Add another booking to ensure the list contains more than one item
        User anotherUser = new User("Alice Johnson", 2, "alice@example.com", 987654321);
        Flight anotherFlight = new Flight("FL201", "Mumbai", "Dubai", LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        Passenger anotherPassenger = new Passenger("Bob Smith", "123456789");
        bookingController.createBooking(anotherUser, Arrays.asList(anotherFlight), Arrays.asList(anotherPassenger));

        List<Booking> bookings = bookingController.getAllBookings();
        assertNotNull(bookings);
        assertTrue(bookings.size() >= 2);
    }
}
