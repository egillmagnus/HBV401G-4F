package com.flight.domain;

/**
 * Represents a passenger in the flight reservation system.
 * A passenger is identified by their name and social security number.
 */
public class Passenger {
    private String name;
    private String socialSecurityNo;

    /**
     * Constructs a new Passenger with the specified name and social security number.
     *
     * @param name The name of the passenger.
     * @param socialSecurityNo The social security number of the passenger.
     */
    public Passenger(String name, String socialSecurityNo) {
        this.name = name;
        this.socialSecurityNo = socialSecurityNo;
    }

    /**
     * Returns the name of the passenger.
     *
     * @return The name of the passenger.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the social security number of the passenger.
     *
     * @return The social security number of the passenger.
     */
    public String getSocialSecurityNo() {
        return socialSecurityNo;
    }

    // Setters
    
    /**
     * Sets the name of the passenger.
     *
     * @param name The new name of the passenger.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the social security number of the passenger.
     *
     * @param socialSecurityNo The new social security number of the passenger.
     */
    public void setSocialSecurityNo(String socialSecurityNo) {
        this.socialSecurityNo = socialSecurityNo;
    }

    /**
     * Returns a string representation of the passenger.
     * 
     * @return A string that represents the passenger details.
     */
    @Override
    public String toString() {
        return "Passenger{" + "\n" +
                "name=" + this.name + "\n" +
                "SSN=" + this.socialSecurityNo + "\n" +
                '}';
    }
}
