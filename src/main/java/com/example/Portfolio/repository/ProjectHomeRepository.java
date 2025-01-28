package com.example.Portfolio.repository;

import com.example.Portfolio.model.ProjectHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectHomeRepository extends JpaRepository<ProjectHome, Long> {
    // Additional custom queries can be defined here
}
