package com.flight.controller;

import com.flight.domain.Flight;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {

    private FlightController flightController;

    @BeforeEach
    void setUp() {
        flightController = new FlightController(); // Initializes the controller and its in-memory database
    }

    @Test
    void testCreateFlight() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 4, 20, 14, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 4, 20, 18, 0);
        Flight flight = flightController.createFlight("FL100", "New York", "London", departureDate, arrivalDate);

        assertNotNull(flight);
        assertEquals("FL100", flight.getFlightNo());
        assertEquals("New York", flight.getOrigin());
        assertEquals("London", flight.getDestination());
    }

    @Test
    void testGetFlight() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 4, 21, 10, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 4, 21, 14, 0);
        flightController.createFlight("FL101", "Tokyo", "San Francisco", departureDate, arrivalDate);

        Flight retrievedFlight = flightController.getFlight("FL101");
        assertNotNull(retrievedFlight);
        assertEquals("FL101", retrievedFlight.getFlightNo());
        assertEquals("Tokyo", retrievedFlight.getOrigin());
    }

    @Test
    void testUpdateFlight() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 5, 1, 16, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 5, 1, 20, 0);
        flightController.createFlight("FL102", "Berlin", "Rome", departureDate, arrivalDate);

        LocalDateTime newDepartureDate = LocalDateTime.of(2024, 5, 2, 16, 0);
        LocalDateTime newArrivalDate = LocalDateTime.of(2024, 5, 2, 20, 0);
        flightController.updateFlight("FL102", "Berlin", "Paris", newDepartureDate, newArrivalDate);

        Flight updatedFlight = flightController.getFlight("FL102");
        assertEquals("Paris", updatedFlight.getDestination());
        assertEquals(newDepartureDate, updatedFlight.getDepartureDate());
    }

    @Test
    void testDeleteFlight() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 6, 10, 11, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 6, 10, 15, 0);
        flightController.createFlight("FL103", "Moscow", "Madrid", departureDate, arrivalDate);

        flightController.deleteFlight("FL103");
        assertNull(flightController.getFlight("FL103"));
    }

    @Test
    void testGetAllFlights() {
        LocalDateTime departureDate1 = LocalDateTime.of(2024, 7, 20, 12, 0);
        LocalDateTime arrivalDate1 = LocalDateTime.of(2024, 7, 20, 16, 0);
        flightController.createFlight("FL104", "Cairo", "Cape Town", departureDate1, arrivalDate1);

        LocalDateTime departureDate2 = LocalDateTime.of(2024, 8, 25, 9, 0);
        LocalDateTime arrivalDate2 = LocalDateTime.of(2024, 8, 25, 12, 0);
        flightController.createFlight("FL105", "Mumbai", "Singapore", departureDate2, arrivalDate2);

        assertEquals(true, flightController.getAllFlights().size() > 0);
    }
}
