package com.flight.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the flight reservation system.
 * A user is identified by their name, user ID, email, phone number, and has a list of bookings.
 */
public class User {
    private String name;
    private int userId;
    private String email;
    private int phone;
    private List<Booking> bookings;

    /**
     * Constructs a new User with the specified name, user ID, email, and phone number.
     * Initializes an empty list of bookings.
     *
     * @param name The name of the user.
     * @param userId The unique identifier for the user.
     * @param email The email address of the user.
     * @param phone The phone number of the user.
     */
    public User(String name, int userId, String email, int phone) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.bookings = new ArrayList<>();
    }

    // Getters and Setters
    
    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user ID.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The new user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phone The new phone number of the user.
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * Returns the list of bookings associated with the user.
     *
     * @return A list of {@link Booking} objects.
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Adds a booking to the user's list of bookings if it is not already present.
     *
     * @param booking The {@link Booking} to be added.
     */
    public void addBooking(Booking booking) {
        if (!bookings.contains(booking)) {
            bookings.add(booking);
        }
    }

    /**
     * Removes a booking from the user's list of bookings.
     *
     * @param booking The {@link Booking} to be removed.
     */
    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
    }

    /**
     * Returns a string representation of the user, including their name, user ID, email, phone number, and bookings.
     * 
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" + "\n" +
                "name=" + name + "\n" +
                "userId=" + userId +
                "email=" + email + "\n" +
                "phone=" + phone + "\n" +
                "bookings=" + bookings + "\n" +
                "}";
    }
}
