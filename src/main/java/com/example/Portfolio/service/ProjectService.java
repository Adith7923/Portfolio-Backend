package com.example.Portfolio.service;

import com.example.Portfolio.model.Project;
import com.example.Portfolio.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get a project by ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null); // Returns the project if found, or null otherwise
    }

    // Get a project by title
    public Optional<Project> getProjectByTitle(String title) {
        return projectRepository.findByTitle(title); // Uses a custom repository method to find by title
    }

    // Create a new project
    public Project createProject(Project project, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            project.setImage(image.getBytes()); // Save image as a byte array
        }
        return projectRepository.save(project);
    }

    // Update an existing project (with image)
    public Project updateProject(Long id, Project projectDetails, MultipartFile image) throws IOException {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            // Update fields
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setTechnologiesUsed(projectDetails.getTechnologiesUsed());
            project.setObjectives(projectDetails.getObjectives());
            project.setReportLink(projectDetails.getReportLink());

            if (image != null && !image.isEmpty()) {
                project.setImage(image.getBytes()); // Update the image if provided
            }

            return projectRepository.save(project);
        }
        return null; // Return null if the project was not found
    }

    // Delete a project by ID
    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true; // Return true if the deletion was successful
        }
        return false; // Return false if the project does not exist
    }
}
