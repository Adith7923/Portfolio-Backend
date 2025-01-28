package com.example.Portfolio.controller;

import com.example.Portfolio.model.Skill;
import com.example.Portfolio.model.Experience;
import com.example.Portfolio.model.Education;
import com.example.Portfolio.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    // Skills CRUD endpoints
    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return aboutService.getAllSkills();
    }

    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill) {
        return aboutService.createSkill(skill);
    }

    @PutMapping("/skills/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        return aboutService.updateSkill(id, skillDetails);
    }

    @DeleteMapping("/skills/{id}")
    public String deleteSkill(@PathVariable Long id) {
        return aboutService.deleteSkill(id) ? "Skill deleted" : "Skill not found";
    }

    // Experience CRUD endpoints
    @GetMapping("/experience")
    public List<Experience> getAllExperience() {
        return aboutService.getAllExperience();
    }

    @PostMapping("/experience")
    public Experience createExperience(@RequestBody Experience experience) {
        return aboutService.createExperience(experience);
    }

    @PutMapping("/experience/{id}")
    public Experience updateExperience(@PathVariable Long id, @RequestBody Experience experienceDetails) {
        return aboutService.updateExperience(id, experienceDetails);
    }

    @DeleteMapping("/experience/{id}")
    public String deleteExperience(@PathVariable Long id) {
        return aboutService.deleteExperience(id) ? "Experience deleted" : "Experience not found";
    }

    // Education CRUD endpoints
    @GetMapping("/education")
    public List<Education> getAllEducation() {
        return aboutService.getAllEducation();
    }

    @PostMapping("/education")
    public Education createEducation(@RequestBody Education education) {
        return aboutService.createEducation(education);
    }

    @PutMapping("/education/{id}")
    public Education updateEducation(@PathVariable Long id, @RequestBody Education educationDetails) {
        return aboutService.updateEducation(id, educationDetails);
    }

    @DeleteMapping("/education/{id}")
    public String deleteEducation(@PathVariable Long id) {
        return aboutService.deleteEducation(id) ? "Education deleted" : "Education not found";
    }
}
