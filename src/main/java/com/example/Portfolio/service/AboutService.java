package com.example.Portfolio.service;

import com.example.Portfolio.model.Skill;
import com.example.Portfolio.model.Experience;
import com.example.Portfolio.model.Education;
import com.example.Portfolio.repository.SkillRepository;
import com.example.Portfolio.repository.ExperienceRepository;
import com.example.Portfolio.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private EducationRepository educationRepository;

    // Skills CRUD operations
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Long id, Skill skillDetails) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            Skill updatedSkill = skill.get();
            updatedSkill.setCategory(skillDetails.getCategory());
            updatedSkill.setName(skillDetails.getName());
            return skillRepository.save(updatedSkill);
        }
        return null;
    }

    public boolean deleteSkill(Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        if (skill.isPresent()) {
            skillRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Experience CRUD operations
    public List<Experience> getAllExperience() {
        return experienceRepository.findAll();
    }

    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Long id, Experience experienceDetails) {
        Optional<Experience> experience = experienceRepository.findById(id);
        if (experience.isPresent()) {
            Experience updatedExperience = experience.get();
            updatedExperience.setTitle(experienceDetails.getTitle());
            updatedExperience.setCompany(experienceDetails.getCompany());
            updatedExperience.setDuration(experienceDetails.getDuration());
            return experienceRepository.save(updatedExperience);
        }
        return null;
    }

    public boolean deleteExperience(Long id) {
        Optional<Experience> experience = experienceRepository.findById(id);
        if (experience.isPresent()) {
            experienceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Education CRUD operations
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    public Education updateEducation(Long id, Education educationDetails) {
        Optional<Education> education = educationRepository.findById(id);
        if (education.isPresent()) {
            Education updatedEducation = education.get();
            updatedEducation.setInstitution(educationDetails.getInstitution());
            updatedEducation.setDegree(educationDetails.getDegree());
            updatedEducation.setDuration(educationDetails.getDuration());
            return educationRepository.save(updatedEducation);
        }
        return null;
    }

    public boolean deleteEducation(Long id) {
        Optional<Education> education = educationRepository.findById(id);
        if (education.isPresent()) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
