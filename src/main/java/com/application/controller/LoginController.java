package com.application.controller;

import com.application.model.Guest;
import com.application.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private GuestRepository guestRepository;


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        Guest admin = getAdmin(email, password);
        if (admin != null) {
            // Admin login successful
            return "redirect:/admin";
        }

        Guest customer = guestRepository.findByEmailAndPassword(email, password);
        if (customer != null) {
            // Customer login successful
            return "redirect:/reservationguest";
        }

        // Invalid login credentials
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    private Guest getAdmin(String email, String password) {
        // Check if the provided credentials match the admin's credentials
        String adminEmail = "admin@gmail.com";
        String adminPassword = "password123";

        if (email.equals(adminEmail) && password.equals(adminPassword)) {
            Guest admin = new Guest();
            admin.setEmail(adminEmail);
            admin.setPassword(adminPassword);
            return admin;
        }

        return null;
    }
}
