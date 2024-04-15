package com.flight.database;

import com.flight.domain.Passenger;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PassengerDB} class simulates a database interface for managing passengers within a flight management system.
 * It offers functionality to insert, delete, and select passengers by their social security numbers, as well as retrieving all passengers.
 */
public class PassengerDB {
    private List<Passenger> passengers;

    /**
     * Initializes a new instance of the PassengerDB class.
     */
    public PassengerDB() {
        passengers = new ArrayList<>();
    }

    /**
     * Retrieves a passenger based on their social security number.
     *
     * @param socialSecurityNo The social security number of the passenger to find.
     * @return The {@link Passenger} if found; otherwise, returns {@code null}.
     */
    public Passenger selectBySocialSecurityNo(String socialSecurityNo) {
        for (Passenger passenger : passengers) {
            if (passenger.getSocialSecurityNo() == socialSecurityNo) {
                System.out.println("Fundin:" + passenger);
                return passenger;
            }
        }
        return null;
    }

    /**
     * Adds a new passenger to the database.
     *
     * @param passenger The {@link Passenger} to add.
     */
    public void insert(Passenger passenger) {
        passengers.add(passenger);
    }

    /**
     * Removes a passenger from the database.
     *
     * @param passenger The {@link Passenger} to remove.
     */
    public void delete(Passenger passenger) {
        if (passenger != null) {
            passengers.removeIf(p -> p.getSocialSecurityNo().equals(passenger.getSocialSecurityNo()));
        }
    }

    /**
     * Retrieves all passengers currently stored in the database.
     *
     * @return A {@link List} of {@link Passenger} objects representing all passengers.
     */
    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers);
    }
}
