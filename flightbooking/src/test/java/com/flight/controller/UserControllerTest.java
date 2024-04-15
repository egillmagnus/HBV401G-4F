package com.flight.controller;

import com.flight.database.UserDB;
import com.flight.domain.Booking;
import com.flight.domain.Flight;
import com.flight.domain.Passenger;
import com.flight.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserController userController;
    private User user;
    private Booking booking1;

    @BeforeEach
    void setUp() {
        userController = new UserController();

        user = new User("John Doe", 1, "johndoe@example.com", 1234567);

        Passenger passenger1 = new Passenger("John Smith", "1234567");


        List<Passenger> passengersList1 = Arrays.asList(passenger1);

    
        LocalDateTime departure1 = LocalDateTime.of(2024, 5, 14, 10, 0);
        LocalDateTime arrival1 = LocalDateTime.of(2024, 5, 14, 12, 0);
        Flight flight1 = new Flight("FL123", "New York", "London", departure1, arrival1);

        
        List<Flight> flightsList1 = Arrays.asList(flight1);
        
        booking1 = new Booking("BK1001", user, flightsList1, passengersList1);

        
        userController.createUser(user.getName(), user.getUserId(), user.getEmail(), user.getPhone());
    }

    @Test
    void testCreateUser() {
        User newUser = userController.createUser("Jane Doe", 2, "janedoe@example.com", 7654321);
        assertNotNull(newUser);
        assertEquals(2, newUser.getUserId());
    }

    @Test
    void testGetUser() {
        User fetchedUser = userController.getUser(1);
        assertNotNull(fetchedUser);
        assertEquals("John Doe", fetchedUser.getName());
    }

    @Test
    void testUpdateUser() {
        userController.updateUser(1, "Updated Name", "updated@example.com", 9876543);
        User updatedUser = userController.getUser(1);
        assertNotNull(updatedUser);
        assertEquals("Updated Name", updatedUser.getName());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    void testDeleteUser() {
        assertNotNull(userController.getUser(1));
        userController.deleteUser(1);
        assertNull(userController.getUser(1));
    }

    @Test
    void testAddAndRemoveBooking() {
        assertTrue(userController.addBookingToUser(1, booking1));
        User fetchedUser = userController.getUser(1);
        assertTrue(fetchedUser.getBookings().contains(booking1));

        userController.removeBookingFromUser(1, booking1);
        fetchedUser = userController.getUser(1);
        assertFalse(fetchedUser.getBookings().contains(booking1));
    }
}
