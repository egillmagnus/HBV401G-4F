package com.flight.database;

import com.flight.domain.Flight;

import java.time.LocalDateTime;
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
        // Sample flight details
        flights.add(new Flight("FI101", "Reykjavík", "Akureyri", LocalDateTime.of(2024, 5, 1, 15, 0), LocalDateTime.of(2024, 5, 1, 16, 0)));
        flights.add(new Flight("FI102", "Reykjavík", "Vestmannaeyjar", LocalDateTime.of(2024, 5, 2, 10, 30), LocalDateTime.of(2024, 5, 2, 11, 15)));
        flights.add(new Flight("FI103", "Reykjavík", "Egilsstaðir", LocalDateTime.of(2024, 5, 3, 12, 0), LocalDateTime.of(2024, 5, 3, 13, 30)));
        flights.add(new Flight("FI104", "Reykjavík", "Ísafjörður", LocalDateTime.of(2024, 5, 4, 14, 0), LocalDateTime.of(2024, 5, 4, 14, 45)));
        flights.add(new Flight("FI105", "Akureyri", "Reykjavík", LocalDateTime.of(2024, 5, 5, 16, 0), LocalDateTime.of(2024, 5, 5, 17, 0)));
        flights.add(new Flight("FI106", "Egilsstaðir", "Reykjavík", LocalDateTime.of(2024, 5, 6, 11, 0), LocalDateTime.of(2024, 5, 6, 12, 30)));
        flights.add(new Flight("FI107", "Ísafjörður", "Reykjavík", LocalDateTime.of(2024, 5, 7, 9, 30), LocalDateTime.of(2024, 5, 7, 10, 15)));
        flights.add(new Flight("FI108", "Vestmannaeyjar", "Reykjavík", LocalDateTime.of(2024, 5, 8, 18, 0), LocalDateTime.of(2024, 5, 8, 18, 45)));
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
