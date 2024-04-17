package com.flight.controller;

import com.flight.database.FlightDB;
import com.flight.domain.Flight;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Retrieves a list of flights departing from a specified city.
     *
     * @param departureCity The city from which the flights depart.
     * @return A list of flights departing from the specified city.
     */
    public List<Flight> getFlightsByDepartureCity(String departureCity) {
        return flightDB.getAllFlights().stream()
                       .filter(flight -> flight.getOrigin().equalsIgnoreCase(departureCity))
                       .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of flights from a specified departure city to a specified arrival city.
     *
     * @param departureCity The departure city.
     * @param arrivalCity The arrival city.
     * @return A list of flights from the departure city to the arrival city.
     */
    public List<Flight> getFlightsFromTo(String departureCity, String arrivalCity) {
        return flightDB.getAllFlights().stream()
                       .filter(flight -> flight.getOrigin().equalsIgnoreCase(departureCity) && flight.getDestination().equalsIgnoreCase(arrivalCity))
                       .collect(Collectors.toList());
    }
}
