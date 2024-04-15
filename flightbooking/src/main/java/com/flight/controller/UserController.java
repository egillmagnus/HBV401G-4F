package com.flight.controller;

import com.flight.database.UserDB;
import com.flight.domain.User;
import com.flight.domain.Booking;

/**
 * Controller class for managing User objects within a flight booking system.
 * This controller uses a mock database to handle User data.
 */
public class UserController {
    private UserDB userDB;

    public UserController() {
        this.userDB = new UserDB();
    }

    /**
     * Creates and adds a new User to the database.
     *
     * @param name The name of the user.
     * @param userId The user's unique identifier.
     * @param email The user's email address.
     * @param phone The user's phone number.
     * @return The newly created User object, or null if the user already exists.
     */
    public User createUser(String name, int userId, String email, int phone) {
        User newUser = new User(name, userId, email, phone);
        userDB.insert(newUser);
        return newUser; // If the user exists, the database won't add it.
    }

    /**
     * Retrieves a User by their user ID.
     *
     * @param userId The user ID of the User to retrieve.
     * @return The User object, or null if not found.
     */
    public User getUser(int userId) {
        return userDB.selectByUserId(userId);
    }

    /**
     * Updates the details of an existing User.
     *
     * @param userId The user ID of the User to update.
     * @param name Optional new name of the User.
     * @param email Optional new email of the User.
     * @param phone Optional new phone number of the User.
     * @return The updated User object, or null if the User does not exist.
     */
    public User updateUser(int userId, String name, String email, Integer phone) {
        User user = getUser(userId);
        if (user != null) {
            if (name != null) user.setName(name);
            if (email != null) user.setEmail(email);
            if (phone != null) user.setPhone(phone);
            return user;
        }
        return null;
    }

    /**
     * Deletes a user from the database.
     *
     * @param userId The user ID of the User to delete.
     */
    public void deleteUser(int userId) {
        userDB.delete(userId);
    }

    /**
     * Adds a booking to a User's list of bookings.
     *
     * @param userId The user ID of the User to whom the booking will be added.
     * @param booking The Booking object to add.
     * @return true if the booking was added successfully, false otherwise.
     */
    public boolean addBookingToUser(int userId, Booking booking) {
        User user = getUser(userId);
        if (user != null) {
            user.addBooking(booking);
            return true;
        }
        return false;
    }

    /**
     * Removes a booking from a User's list of bookings.
     *
     * @param userId The user ID of the User from whom the booking will be removed.
     * @param booking The Booking object to remove.
     * @return true if the booking was removed successfully, false otherwise.
     */
    public boolean removeBookingFromUser(int userId, Booking booking) {
        User user = getUser(userId);
        if (user != null && user.getBookings().contains(booking)) {
            user.cancelBooking(booking);
            return true;
        }
        return false;
    }
}
