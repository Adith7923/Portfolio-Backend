package com.example.Portfolio.controller;

import com.example.Portfolio.model.Skill;
import com.example.Portfolio.model.Experience;
import com.example.Portfolio.model.Education;
import com.example.Portfolio.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    // 🔓 Public - Anyone can view skills
    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return aboutService.getAllSkills();
    }

    // 🔒 Admin Only - Create, Update, Delete Skills
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill) {
        return aboutService.createSkill(skill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skills/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        return aboutService.updateSkill(id, skillDetails);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skills/{id}")
    public String deleteSkill(@PathVariable Long id) {
        return aboutService.deleteSkill(id) ? "Skill deleted" : "Skill not found";
    }

    // 🔓 Public - Anyone can view experience
    @GetMapping("/experience")
    public List<Experience> getAllExperience() {
        return aboutService.getAllExperience();
    }

    // 🔒 Admin Only - Create, Update, Delete Experience
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/experience")
    public Experience createExperience(@RequestBody Experience experience) {
        return aboutService.createExperience(experience);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experience/{id}")
    public Experience updateExperience(@PathVariable Long id, @RequestBody Experience experienceDetails) {
        return aboutService.updateExperience(id, experienceDetails);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experience/{id}")
    public String deleteExperience(@PathVariable Long id) {
        return aboutService.deleteExperience(id) ? "Experience deleted" : "Experience not found";
    }

    // 🔓 Public - Anyone can view education
    @GetMapping("/education")
    public List<Education> getAllEducation() {
        return aboutService.getAllEducation();
    }

    // 🔒 Admin Only - Create, Update, Delete Education
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/education")
    public Education createEducation(@RequestBody Education education) {
        return aboutService.createEducation(education);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/education/{id}")
    public Education updateEducation(@PathVariable Long id, @RequestBody Education educationDetails) {
        return aboutService.updateEducation(id, educationDetails);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/education/{id}")
    public String deleteEducation(@PathVariable Long id) {
        return aboutService.deleteEducation(id) ? "Education deleted" : "Education not found";
    }
}
