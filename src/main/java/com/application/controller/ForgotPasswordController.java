package com.application.controller;


import com.application.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class ForgotPasswordController {

    private final DataSource dataSource;

    @Autowired
    public ForgotPasswordController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/forgotpasswordform")
    public String forgotPasswordRequest(Model model) {
        model.addAttribute("guest", new Guest());
        return "forgotpassword";
    }

    @PostMapping("/resetpassword")
    @ResponseBody
    public String resetPassword(@ModelAttribute("forgotPasswordRequest") Guest guest, Model model) {
        String email = guest.getEmail();
        String password = getPasswordFromDatabase(email);

        if (password != null) {
            model.addAttribute("successMessage", "An email with instructions to reset your password has been sent to your email address.");
            return "success";
        } else {
            model.addAttribute("errorMessage", "The provided email address does not exist in our records.");
            return "notfound";
        }
    }

    private String getPasswordFromDatabase(String email) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT password FROM Guest WHERE email = '" + email + "'")) {
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            // Handle the exception
        }
        return null;
    }
}
