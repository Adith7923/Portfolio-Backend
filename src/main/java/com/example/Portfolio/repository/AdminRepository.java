package com.example.Portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Portfolio.model.Admin;


import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Admin findByUsername(String username);
}