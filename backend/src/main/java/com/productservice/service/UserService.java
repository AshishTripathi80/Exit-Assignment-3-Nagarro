package com.productservice.service;

import com.productservice.model.User;

public interface UserService {

    User registerUser(User user);
    User loginUser(String email, String password);
    void logOut(String email);
}
