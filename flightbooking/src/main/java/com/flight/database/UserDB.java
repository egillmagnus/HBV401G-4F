package com.flight.database;

import com.flight.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UserDB} class simulates a database interface for managing users within a flight management system.
 * It offers functionality to insert, delete, and select users by their user ID, as well as retrieving all users.
 */
public class UserDB {
    private List<User> users;

    /**
     * Initializes a new instance of the UserDB class.
     */
    public UserDB() {
        users = new ArrayList<>();
    }

    /**
     * Retrieves a user based on their user ID.
     *
     * @param userId The user ID of the user to find.
     * @return The {@link User} if found; otherwise, returns {@code null}.
     */
    public User selectByUserId(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                System.out.println("Found: " + user);
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a new user to the database.
     *
     * @param user The {@link User} to add.
     */
    public void insert(User user) {
        if (selectByUserId(user.getUserId()) == null) {
            users.add(user);
        } else {
            System.out.println("User already exists with ID: " + user.getUserId());
        }
    }

    /**
     * Removes a user from the database.
     *
     * @param userId The user ID of the User to remove.
     */
    public void delete(int userId) {
        User existingUser = selectByUserId(userId);
        if (existingUser != null) {
            users.removeIf(u -> u.getUserId() == userId);
            System.out.println("User removed: " + existingUser);
        } else {
            System.out.println("No user found with ID: " + userId);
        }
    }

    /**
     * Retrieves all users currently stored in the database.
     *
     * @return A {@link List} of {@link User} objects representing all users.
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
