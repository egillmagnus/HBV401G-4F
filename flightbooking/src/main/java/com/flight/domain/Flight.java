package com.flight.domain;
import java.time.LocalDateTime;

/**
 * Represents a flight in the flight reservation system.
 * A flight is characterized by its flight number, origin, destination, departure date, and arrival date.
 */
public class Flight {
    private String flightNo;
    private String origin;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    /**
     * Constructs a new Flight with the specified flight number, origin, destination, departure date, and arrival date.
     *
     * @param flightNo The flight's unique identifier.
     * @param origin The city or airport of departure.
     * @param destination The city or airport of arrival.
     * @param departureDate The date and time of departure.
     * @param arrivalDate The date and time of arrival.
     */
    public Flight(String flightNo, String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    // Getters and setters
    
    /**
     * Returns the flight number.
     *
     * @return The flight number as a String.
     */
    public String getFlightNo() {
        return flightNo;
    }

    /**
     * Sets the flight number.
     *
     * @param flightNo The flight number as a String.
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    /**
     * Returns the origin of the flight.
     *
     * @return The origin city or airport as a String.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of the flight.
     *
     * @param origin The origin city or airport as a String.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Returns the destination of the flight.
     *
     * @return The destination city or airport as a String.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of the flight.
     *
     * @param destination The destination city or airport as a String.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns the departure date and time.
     *
     * @return The departure date and time as a LocalDateTime.
     */
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date and time.
     *
     * @param departureDate The departure date and time as a LocalDateTime.
     */
    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Returns the arrival date and time.
     *
     * @return The arrival date and time as a LocalDateTime.
     */
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the arrival date and time.
     *
     * @param arrivalDate The arrival date and time as a LocalDateTime.
     */
    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    /**
     * Returns a string representation of the flight.
     * 
     * @return A string that represents the flight details.
     */
    @Override
    public String toString() {
        return "Flight{" + "\n" +
                "flightNo=" + flightNo + "\n" +
                "origin=" + origin + "\n" +
                "destination='" + destination + "\n" +
                "departureDate=" + departureDate + "\n" +
                "arrivalDate=" + arrivalDate + "\n" +
                '}';
    }
}
