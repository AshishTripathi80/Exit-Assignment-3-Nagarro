package com.productservice.service;

import com.productservice.model.User;
import com.productservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists.");
        }
        user.setStatus("OFFLINE");
        return userRepository.save(user);
    }


    public User loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if the password matches
            if (user.getPassword().equals(password)) {
                user.setStatus("ONLINE");
                return userRepository.save(user);
            } else {
                throw new RuntimeException("Incorrect password.");
            }
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    public void logOut(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setStatus("OFFLINE");
            userRepository.save(user);
        });
    }
}
