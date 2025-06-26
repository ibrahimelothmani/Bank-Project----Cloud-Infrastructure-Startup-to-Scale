package com.example.hotel_reservation_system.Service;


import com.example.hotel_reservation_system.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public void setUser(int userId, int balance) {
        User existingUser = findUser(userId);
        if (existingUser == null) {
            users.add(new User(userId, balance));
        } else {
            existingUser.setBalance(balance);
        }
    }

    public User findUser(int userId) {
        return users.stream()
                .filter(u -> u.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void printAllUsers() {
        System.out.println("Users:");
        for (int i = users.size() - 1; i >= 0; i--) {
            User u = users.get(i);
            System.out.printf("User %d, Balance: %d\n", u.getUserId(), u.getBalance());
        }
    }
}

