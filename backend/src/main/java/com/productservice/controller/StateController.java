package com.productservice.controller;

import com.productservice.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/state/")
@CrossOrigin(origins = "http://localhost:4200")
public class StateController {

    @Autowired
    private StateService stateService;

    // API endpoint to get state data
    @GetMapping("getState")
    public Map<String, Long> getState() {
        Map<String, Long> state = new HashMap<>();
        state.put("totalUsers", stateService.getTotalUsers());
        state.put("onlineUsers", stateService.getOnlineUsers());
        state.put("totalProducts", stateService.getTotalProducts());
        return state;
    }
}
