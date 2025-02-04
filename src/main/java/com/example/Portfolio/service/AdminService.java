package com.example.Portfolio.service;

import com.example.Portfolio.model.Admin;
import com.example.Portfolio.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register new admin
    public String registerAdmin(Admin admin) {
        // Check if user already exists
        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            return "User already exists"; // Return message if admin exists
        }

        // Hash the password before saving
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        // Save the admin
        adminRepository.save(admin);
        return "User registered successfully"; // Return success message
    }

    // Get all registered users
    public List<Admin> getAllUsers() {
        return adminRepository.findAll(); // Fetch all users
    }

    // Delete user by UUID
    public String deleteUser(UUID id) {
        Optional<Admin> admin = adminRepository.findById(id); // Find user by ID
        if (admin.isPresent()) {
            adminRepository.deleteById(id); // Delete the user
            return "User deleted successfully"; // Return success message
        } else {
            return "User not found"; // Return error message if user not found
        }
    }
}
