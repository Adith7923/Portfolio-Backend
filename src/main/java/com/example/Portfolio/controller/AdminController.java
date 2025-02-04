package com.example.Portfolio.controller;

import com.example.Portfolio.model.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Portfolio.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "http://localhost:5173/*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        String result = adminService.registerAdmin(admin);

        if (result.equals("User already exists")) {
            return ResponseEntity.status(400).body(result); // Bad Request if user already exists
        } else if (result.equals("User registered successfully")) {
            return ResponseEntity.status(201).body(result); // Created
        } else {
            return ResponseEntity.status(500).body("Something went wrong"); // Internal Server Error
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin() {
        return ResponseEntity.ok("Login Successful"); // If authentication passes, Spring Security handles login
    }

    @GetMapping("/users")
    public ResponseEntity<List<Admin>> getAllUsers() {
        List<Admin> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // DELETE endpoint to remove a user by UUID
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        String result = adminService.deleteUser(id);
        if (result.equals("User not found")) {
            return ResponseEntity.status(404).body(result); // User not found
        } else if (result.equals("User deleted successfully")) {
            return ResponseEntity.status(200).body(result); // User deleted
        } else {
            return ResponseEntity.status(500).body("Something went wrong"); // Internal Server Error
        }
    }
}
