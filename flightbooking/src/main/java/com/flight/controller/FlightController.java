package com.flight.controller;

import com.flight.database.FlightDB;
import com.flight.domain.Flight;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class for managing Flight objects within a flight booking system.
 * This class provides methods to create, retrieve, update, and delete flights,
 * as well as to list all available flights.
 */
public class FlightController {
    private FlightDB flightDB;

    /**
     * Constructs a new FlightController and initializes its flight database.
     */
    public FlightController() {
        this.flightDB = new FlightDB();
    }

    /**
     * Creates a new flight and adds it to the database.
     *
     * @param flightNo The unique identifier of the flight.
     * @param origin The departure city or airport.
     * @param destination The arrival city or airport.
     * @param departureDate The departure date and time.
     * @param arrivalDate The arrival date and time.
     * @return The newly created Flight object.
     */
    public Flight createFlight(String flightNo, String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate) {
        Flight flight = new Flight(flightNo, origin, destination, departureDate, arrivalDate);
        flightDB.insert(flight);
        return flight;
    }

    /**
     * Retrieves a flight from the database using its flight number.
     *
     * @param flightNo The flight number of the flight to retrieve.
     * @return The Flight object if found; null otherwise.
     */
    public Flight getFlight(String flightNo) {
        return flightDB.getByFlightNo(flightNo);
    }

    /**
     * Updates the details of an existing flight.
     *
     * @param flightNo The flight number of the flight to update.
     * @param newOrigin The new departure city or airport.
     * @param newDestination The new arrival city or airport.
     * @param newDepartureDate The new departure date and time.
     * @param newArrivalDate The new arrival date and time.
     * @return The updated Flight object.
     */
    public Flight updateFlight(String flightNo, String newOrigin, String newDestination, LocalDateTime newDepartureDate, LocalDateTime newArrivalDate) {
        Flight flight = new Flight(flightNo, newOrigin, newDestination, newDepartureDate, newArrivalDate);
        flightDB.update(flight);
        return flightDB.getByFlightNo(flightNo);
    }

    /**
     * Deletes a flight from the database using its flight number.
     *
     * @param flightNo The flight number of the flight to be deleted.
     */
    public void deleteFlight(String flightNo) {
        flightDB.delete(flightNo);
    }

    /**
     * Retrieves a list of all flights currently stored in the database.
     *
     * @return A list of all flights.
     */
    public List<Flight> getAllFlights() {
        return flightDB.getAllFlights();
    }
}
