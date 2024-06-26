package com.flight.app;

import com.flight.controller.BookingController;
import com.flight.controller.FlightController;
import com.flight.controller.PassengerController;
import com.flight.controller.UserController;
import com.flight.domain.Booking;
import com.flight.domain.Flight;
import com.flight.domain.Passenger;
import com.flight.domain.User;

import java.time.LocalDate;
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
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
    
        System.setProperty("file.encoding", "UTF-8");
        while (running) {
            System.out.println("\nWelcome to the Flight Booking System");
            System.out.println("1. Book Flights");
            System.out.println("2. Manage Flights");
            System.out.println("3. Manage Bookings");
            System.out.println("4. Manage Passengers");
            System.out.println("5. Exit");
    
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1:
                    bookFlight();
                    break;
                case 2:
                    manageFlights();
                    break;
                case 3:
                    manageBookings();
                    break;
                case 4:
                    managePassengers();
                    break;
                case 5:
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
            scanner.nextLine();

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

        List<Flight> flights = flightController.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("No flights available to update.");
            return;
        }
    
        System.out.println("\nAvailable Flights:");
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
        List<Flight> selectedFlights = new ArrayList<>();

        while (true) {
            System.out.println("\nBooking a Flight:");
            System.out.println("Choose an option:");
            System.out.println("1. See all available flights");
            System.out.println("2. See all flights from a departure city");
            System.out.println("3. See all flights from a departure city to an arrival city");
            System.out.println("4. See all flights from a departure city to an arrival city on a specific date");
            System.out.println("5. See flights in cart.");
            System.out.println("6. Clear cart");
            System.out.println("7. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 7) {
                System.out.println("Returning to Main Menu...");
                return; 
            }

            List<Flight> flights = new ArrayList<>();
            String departureCity, arrivalCity;
            LocalDateTime departureDate;

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
                case 4:
                    System.out.print("Enter departure city: ");
                    departureCity = scanner.nextLine();
                    System.out.print("Enter arrival city: ");
                    arrivalCity = scanner.nextLine();
                    System.out.print("Enter departure date (yyyy-MM-dd): ");
                    departureDate = LocalDate.parse(scanner.nextLine(), dateFormatter).atStartOfDay();
                    flights = flightController.getFlightsFromToByDate(departureCity, arrivalCity, departureDate);
                    break;
                case 5:
                    if(selectedFlights.size() == 0) {
                        System.out.println("Your cart is empty");
                        break;
                    }
                    for(int i = 0; i < selectedFlights.size(); i++) {
                        Flight flight = selectedFlights.get(i);
                        System.out.printf("%d. %s from %s to %s on %s\n", i + 1, flight.getFlightNo(), flight.getOrigin(), flight.getDestination(), flight.getDepartureDate().format(formatter));
                    }
                    break;
                case 6:
                    selectedFlights = new ArrayList<>();
                    System.out.println("Cart emptyed!");
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            if (flights.isEmpty()) {
                System.out.println("No flights available for the selected criteria.");
                continue;
            }
    
            System.out.println("Available Flights:");
            for (int i = 0; i < flights.size(); i++) {
                Flight flight = flights.get(i);
                System.out.printf("%d. %s from %s to %s on %s\n", i + 1, flight.getFlightNo(), flight.getOrigin(), flight.getDestination(), flight.getDepartureDate().format(formatter));
            }
    
            System.out.print("\nSelect a flight number to book or -1 to finish selecting flights: ");
            int flightIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            if (flightIndex == -2) {
                System.out.println("Finishing selecting flights");
                break;
            } else if (flightIndex < 0 || flightIndex >= flights.size()) {
                System.out.println("Invalid flight selection.");
                continue;
            } else {
                Flight selectedFlight = flights.get(flightIndex);
                System.out.println("Flight " + selectedFlight.getFlightNo() + " added to cart");
                selectedFlights.add(selectedFlight);
            }
        }
    
        if (selectedFlights.isEmpty()) {
            System.out.println("No flights have been selected.");
            return;
        } 
    
        List<Passenger> passengers = new ArrayList<>();
        System.out.print("\nEnter number of passengers for all selected flights: ");
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
            Booking booking = bookingController.createBooking(user, selectedFlights, passengers);
            if (booking.getBookingNo() != null) {
                System.out.println("\nBooking created successfully with booking number: " + booking.getBookingNo());
            } else {
                System.out.println("\nBooking failed. Please try again.");
            }
        } else {
            System.out.println("\nFailed to create bookings. No valid user.");
        }
    }
    

    private static User createUser() {
        System.out.println("\nRegistering New User:");
        System.out.print("\nEnter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        int phone = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter a unique user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User newUser = userController.createUser(name, userId, email, phone);
        if (newUser == null) {
            System.out.println("\nA user with this ID already exists. Please try again.");
            return null;
        } else {
            System.out.println("\nUser created successfully!");
            return newUser;
        }
    }


    private static void manageBookings() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\nBooking Management:");
            System.out.println("1. View All Bookings");
            System.out.println("2. Delete Booking");
            System.out.println("3. Return to Main Menu");
    
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();     
            switch (option) {
                case 1:
                    viewAllBookings();
                    break;
                case 2:
                    deleteBooking();
                    break;
                case 3:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please choose again.\n");
            }
        }
    }

    private static void viewAllBookings() {
        List<Booking> bookings = bookingController.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings available.");
        } else {
            for (int i = 0; i < bookings.size(); i++) {
                Booking booking = bookings.get(i);
                System.out.println("");
                System.out.println("Booking Number: " + booking.getBookingNo());
                System.out.println("User: " + booking.getUser().getName());
                for (Flight flight : booking.getFlights()) {
                    System.out.println("Flight: " + flight.getFlightNo() + " from " 
                                        + flight.getOrigin() + " to " + flight.getDestination() 
                                        + ", on " + flight.getDepartureDate().format(formatter));
                }
                for (Passenger passenger : booking.getPassengers()) {
                    System.out.println("Passenger: " + passenger.getName());
                }
                System.out.println();
            }
        }
    }

    private static void deleteBooking() {
        System.out.print("\nEnter the booking number to delete: ");
        String bookingNo = scanner.nextLine();
        Booking booking = bookingController.getBooking(bookingNo);
        if (booking == null) {
            System.out.println("\nNo booking found with the provided number.");
            return;
        }

        bookingController.deleteBooking(bookingNo);
        System.out.println("\nBooking deleted successfully.");
    }

    private static void managePassengers() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\nPassenger Management:");
            System.out.println("1. View All Passengers");
            System.out.println("2. Delete Passenger");
            System.out.println("3. Return to Main Menu");
    
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline
    
            switch (option) {
                case 1:
                    viewAllPassengers();
                    break;
                case 2:
                    deletePassenger();
                    break;
                case 3:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
    
    private static void viewAllPassengers() {
        List<Passenger> passengers = passengerController.getAllPassengers();
        if (passengers.isEmpty()) {
            System.out.println("No passengers found.");
        } else {
            passengers.forEach(passenger -> System.out.println("Passenger SSN: " + passenger.getSocialSecurityNo() + ", Name: " + passenger.getName()));
        }
    }
    
    private static void deletePassenger() {
        System.out.print("Enter the SSN of the passenger to delete: ");
        String ssn = scanner.nextLine();
        boolean success = passengerController.deletePassenger(ssn);
        if (success) {
            System.out.println("Passenger deleted successfully.");
        } else {
            System.out.println("Passenger not found.");
        }
    }
    
    
}
