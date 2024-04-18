package com.flight.controller;

import com.flight.domain.Passenger;

import java.util.List;

import com.flight.database.PassengerDB;

/**
 * The {@code PassengerController} class is responsible for managing passenger data by interacting with the passenger database.
 * It provides functionalities to find, create, and delete passengers in the system.
 */
public class PassengerController {
    private final PassengerDB passengerDB;

    /**
     * Constructs a new {@code PassengerController} with a specified passenger database.
     *
     * @param passengerDB The passenger database used for data operations.
     */
    public PassengerController() {
        this.passengerDB = new PassengerDB();
    }

    /**
     * Finds a passenger by their social security number.
     *
     * @param socialSecurityNo The social security number of the passenger to be found.
     * @return A {@link Passenger} object if a passenger with the specified social security number exists; {@code null} otherwise.
     */
    public Passenger findPassenger(String socialSecurityNo) {
        return passengerDB.selectBySocialSecurityNo(socialSecurityNo);
    }

    /**
     * Creates a new passenger in the system if they do not already exist.
     *
     * @param passenger The {@link Passenger} object to be created.
     * Note: The passenger is created only if there is no existing passenger with the same social security number.
     */
    public void createPassenger(Passenger passenger) {
    
        if (passengerDB.selectBySocialSecurityNo(passenger.getSocialSecurityNo()) == null) {
            passengerDB.insert(passenger);
        }
    }

    /**
     * Deletes a passenger from the system.
     *
     * @param passenger The {@link Passenger} object to be deleted.
     */
    public boolean deletePassenger(String ssn) {

        return passengerDB.delete(ssn);
    }

    /**
     * Retrieves all passengers currently stored in the database.
     *
     * @return A {@link List} of {@link Passenger} objects representing all passengers.
     */
    public List<Passenger> getAllPassengers() {
        return passengerDB.getAllPassengers();
    }
}
