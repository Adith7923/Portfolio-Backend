package com.example.Portfolio.repository;

import com.example.Portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    // Custom queries can be added here if needed
}
