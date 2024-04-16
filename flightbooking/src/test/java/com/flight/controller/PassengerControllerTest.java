package com.flight.controller;

import com.flight.domain.Passenger;
import com.flight.database.PassengerDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test fixture for PassengerController.
 */
public class PassengerControllerTest {

    private PassengerController controller;

    @BeforeEach
    void setUp() {
        controller = new PassengerController();

        // Set up initial state for the database
        controller.createPassenger(new Passenger("John Doe", "1234567890"));
        controller.createPassenger(new Passenger("Jane Smith", "0987654321"));
    }

    @Test
    void testFindPassenger() {
        Passenger foundPassenger = controller.findPassenger("1234567890");
        assertNotNull(foundPassenger);
        assertEquals("John Doe", foundPassenger.getName());
    }

    @Test
    void testCreateAndDeletePassenger() {
        Passenger newPassenger = new Passenger("Alice Jones", "1122334455");
        controller.createPassenger(newPassenger);

        Passenger retrievedPassenger = controller.findPassenger("1122334455");
        System.out.println(retrievedPassenger);
        assertTrue(retrievedPassenger != null);
        assertEquals("Alice Jones", retrievedPassenger.getName());

        controller.deletePassenger(newPassenger);
        retrievedPassenger = controller.findPassenger("1122334455");
        assertFalse(retrievedPassenger != null);
    }
}