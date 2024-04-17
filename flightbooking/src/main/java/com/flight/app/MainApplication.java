package com.flight.app;

import com.flight.controller.BookingController;
import com.flight.controller.FlightController;
import com.flight.controller.PassengerController;
import com.flight.controller.UserController;
import com.flight.domain.Booking;
import com.flight.domain.Flight;
import com.flight.domain.Passenger;
import com.flight.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static BookingController bookingController = new BookingController();
    private static FlightController flightController = new FlightController();
    private static PassengerController passengerController = new PassengerController();
    private static UserController userController = new UserController();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Flight Booking System");
            System.out.println("1. Manage Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    manageFlights();
                    break;
                case 2:
                    bookFlight();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Flight Booking System!");
    }

    private static void manageFlights() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\nFlight Management:");
            System.out.println("1. Create Flight");
            System.out.println("2. Update Flight");
            System.out.println("3. Return to Main Menu");

            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (option) {
                case 1:
                    createFlight();
                    break;
                case 2:
                    updateFlight();
                    break;
                case 3:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void createFlight() {
        System.out.println("Creating a New Flight:");
        System.out.print("Enter flight number: ");
        String flightNo = scanner.nextLine();
        System.out.print("Enter origin: ");
        String origin = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter departure date and time (yyyy-MM-dd HH:mm): ");
        LocalDateTime departureDate = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter arrival date and time (yyyy-MM-dd HH:mm): ");
        LocalDateTime arrivalDate = LocalDateTime.parse(scanner.nextLine(), formatter);

        Flight flight = flightController.createFlight(flightNo, origin, destination, departureDate, arrivalDate);
        System.out.println("Flight created successfully: " + flight.getFlightNo());
    }

    private static void updateFlight() {
        System.out.println("Updating an Existing Flight:");

        // Display all available flights
        List<Flight> flights = flightController.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("No flights available to update.");
            return;
        }
    
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            System.out.printf("Flight Number: %s, From: %s, To: %s, Departure: %s, Arrival: %s\n",
                              flight.getFlightNo(), flight.getOrigin(), flight.getDestination(),
                              flight.getDepartureDate().format(formatter), flight.getArrivalDate().format(formatter));
        }
        System.out.print("Enter flight number to update: ");
        String flightNo = scanner.nextLine();
        Flight flight = flightController.getFlight(flightNo);

        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        System.out.print("Enter new origin (current: " + flight.getOrigin() + "): ");
        String newOrigin = scanner.nextLine();
        System.out.print("Enter new destination (current: " + flight.getDestination() + "): ");
        String newDestination = scanner.nextLine();
        System.out.print("Enter new departure date and time (yyyy-MM-dd HH:mm, current: " + flight.getDepartureDate().format(formatter) + "): ");
        LocalDateTime newDepartureDate = LocalDateTime.parse(scanner.nextLine(), formatter);
        System.out.print("Enter new arrival date and time (yyyy-MM-dd HH:mm, current: " + flight.getArrivalDate().format(formatter) + "): ");
        LocalDateTime newArrivalDate = LocalDateTime.parse(scanner.nextLine(), formatter);

        flight = flightController.updateFlight(flightNo, newOrigin, newDestination, newDepartureDate, newArrivalDate);
        System.out.println("Flight updated successfully: " + flight.getFlightNo());
    }


    private static void bookFlight() {
        System.out.println("Booking a Flight:");
    
        System.out.println("Choose an option:");
        System.out.println("1. See all available flights");
        System.out.println("2. See all flights from a departure city");
        System.out.println("3. See all flights from a departure city to an arrival city");
        System.out.print("Enter your choice: ");
        int option = scanner.nextInt();
        scanner.nextLine(); 
    
        List<Flight> flights = new ArrayList<>();
        String departureCity, arrivalCity;
    
        switch (option) {
            case 1:
                flights = flightController.getAllFlights();
                break;
            case 2:
                System.out.print("Enter departure city: ");
                departureCity = scanner.nextLine();
                flights = flightController.getFlightsByDepartureCity(departureCity);
                break;
            case 3:
                System.out.print("Enter departure city: ");
                departureCity = scanner.nextLine();
                System.out.print("Enter arrival city: ");
                arrivalCity = scanner.nextLine();
                flights = flightController.getFlightsFromTo(departureCity, arrivalCity);
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
    
        if (flights.isEmpty()) {
            System.out.println("No flights available for the selected criteria.");
            return;
        }
    
        System.out.println("Available Flights:");
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.printf("%d. %s from %s to %s on %s\n", i + 1, flight.getFlightNo(), flight.getOrigin(), flight.getDestination(), flight.getDepartureDate().format(formatter));
        }
    

        System.out.print("Select a flight number to book: ");
        int flightIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // consume the newline
        if (flightIndex < 0 || flightIndex >= flights.size()) {
            System.out.println("Invalid flight selection.");
            return;
        }
        Flight selectedFlight = flights.get(flightIndex);
    
        List<Passenger> passengers = new ArrayList<>();
        System.out.print("Enter number of passengers: ");
        int numPassengers = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numPassengers; i++) {
            System.out.print("Enter passenger name: ");
            String name = scanner.nextLine();
            System.out.print("Enter social security number: ");
            String ssn = scanner.nextLine();
            Passenger passenger = new Passenger(name, ssn);
            passengerController.createPassenger(passenger);
            passengers.add(passenger);
        }
    
        System.out.print("Enter user ID for booking or press 0 to register a new user: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = null;
        if (userId == 0) {
            user = createUser();
        } else {
            user = userController.getUser(userId);
        }

        if (user == null) {
            System.out.println("User not found. Registering new user.");
            user = createUser();
        }

        if (user != null) {
            System.out.print("Enter booking number: ");
            Booking booking = bookingController.createBooking(user, List.of(selectedFlight), passengers);
            if (booking.getBookingNo() == null) {
                System.out.println("Booking failed, not enough avalible seats, please try again.");
            } else {
                System.out.println("Booking created successfully with booking number: " + booking.getBookingNo());
            }
        } else {
            System.out.println("Failed to create booking. No valid user.");
        }
    }

    private static User createUser() {
        System.out.println("Registering New User:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        int phone = scanner.nextInt();
        scanner.nextLine();  // consume the newline
        System.out.print("Enter a unique user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // consume the newline

        User newUser = userController.createUser(name, userId, email, phone);
        if (newUser == null) {
            System.out.println("A user with this ID already exists. Please try again.");
            return null;
        } else {
            System.out.println("User created successfully!");
            return newUser;
        }
    }
}
