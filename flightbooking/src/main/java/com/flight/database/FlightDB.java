package com.flight.database;

import com.flight.domain.Flight;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates a database interface for managing flights within a flight reservation system.
 * Provides functionality to insert, retrieve, update, and delete flights, as well as listing all flights.
 */
public class FlightDB {
    private List<Flight> flights;

    /**
     * Initializes a new instance of the FlightDB class.
     * Starts with an empty list of flights.
     */
    public FlightDB() {
        this.flights = new ArrayList<>();
    }

    /**
     * Inserts a new flight into the database if it does not already exist.
     * 
     * @param flight The Flight object to be added.
     */
    public void insert(Flight flight) {
        if (getByFlightNo(flight.getFlightNo()) == null) {
            flights.add(flight);
        }
    }

    /**
     * Retrieves a flight from the database based on its flight number.
     * 
     * @param flightNo The flight number to search for.
     * @return The Flight object if found; null otherwise.
     */
    public Flight getByFlightNo(String flightNo) {
        for (Flight flight : flights) {
            if (flight.getFlightNo().equals(flightNo)) {
                return flight;
            }
        }
        return null;
    }

    /**
     * Updates the details of an existing flight in the database.
     * Only updates the flight if it already exists.
     *
     * @param flight The Flight object containing the updated details.
     */
    public void update(Flight flight) {
        Flight existingFlight = getByFlightNo(flight.getFlightNo());
        if (existingFlight != null) {
            existingFlight.setOrigin(flight.getOrigin());
            existingFlight.setDestination(flight.getDestination());
            existingFlight.setDepartureDate(flight.getDepartureDate());
            existingFlight.setArrivalDate(flight.getArrivalDate());
        }
    }

    /**
     * Deletes a flight from the database using its flight number.
     *
     * @param flightNo The flight number of the flight to be deleted.
     */
    public void delete(String flightNo) {
        flights.removeIf(f -> f.getFlightNo().equals(flightNo));
    }

    /**
     * Retrieves all flights currently stored in the database.
     *
     * @return A list of all flights.
     */
    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights);
    }
}
