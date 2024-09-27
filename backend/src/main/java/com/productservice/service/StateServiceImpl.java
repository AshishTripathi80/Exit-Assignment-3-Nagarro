package com.productservice.service;

import com.productservice.repo.ProductRepository;
import com.productservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // Count total users
    public long getTotalUsers() {
        return userRepository.count();
    }

    // Count online users
    public long getOnlineUsers() {
        return userRepository.countByStatus("ONLINE");
    }

    // Count total products
    public long getTotalProducts() {
        return productRepository.count();
    }
}
