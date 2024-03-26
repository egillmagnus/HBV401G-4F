package com.flight.domain;
import java.util.List;

/**
 * Represents a booking within a flight reservation system.
 * A booking consists of one or more flights, passengers, a user who made the booking, and a unique booking number.
 */
public class Booking {
    private List<Passenger> passengers;
    private List<Flight> flights;
    private User user;
    private String bookingNo;
    
    /**
     * Creates a booking with the specified booking number, user, flights, and passengers.
     * 
     * @param bookingNo The unique booking number.
     * @param user The user who made the booking.
     * @param flights A list of flights included in the booking.
     * @param passengers A list of passengers included in the booking.
     */
    public Booking(String bookingNo, User user, List<Flight> flights, List<Passenger> passengers) {
        this.bookingNo = bookingNo;
        this.user = user;
        this.flights = flights;
        this.passengers = passengers;
    }

    // Getters and Setters
    
    /**
     * Returns the list of passengers in the booking.
     * 
     * @return A list of {@link Passenger} objects.
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Sets the list of passengers for the booking.
     * 
     * @param passengers A list of {@link Passenger} objects.
     */
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    /**
     * Returns the list of flights in the booking.
     * 
     * @return A list of {@link Flight} objects.
     */
    public List<Flight> getFlights() {
        return flights;
    }

    /**
     * Sets the list of flights for the booking.
     * 
     * @param flights A list of {@link Flight} objects.
     */
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * Returns the user who made the booking.
     * 
     * @return A {@link User} object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user for the booking.
     * 
     * @param user A {@link User} object.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the booking number.
     * 
     * @return The booking number as a {@code String}.
     */
    public String getBookingNo() {
        return bookingNo;
    }

    /**
     * Sets the booking number.
     * 
     * @param bookingNo The booking number as a {@code String}.
     */
    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }
    
    /**
     * Adds a passenger to the booking.
     * 
     * @param passenger The {@link Passenger} to add to the booking.
     */
    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    /**
     * Adds a flight to the booking.
     * 
     * @param flight The {@link Flight} to add to the booking.
     */
    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }
}
