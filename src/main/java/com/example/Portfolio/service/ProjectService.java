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

    // Get project by title
    public Optional<Project> getProjectByTitle(String title) {
        return projectRepository.findByTitle(title); // Use the custom method
    }

    // Create a new project
    public Project createProject(Project project, MultipartFile image) throws IOException {
        project.setImage(image.getBytes()); // Save image as byte array in the database
        return projectRepository.save(project);
    }

    // Update project (with image)
    public Project updateProject(Long id, Project projectDetails, MultipartFile image) throws IOException {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setTechnologiesUsed(projectDetails.getTechnologiesUsed());
            project.setReportLink(projectDetails.getReportLink());
            project.setImage(image.getBytes()); // Update image if a new one is uploaded
            return projectRepository.save(project);
        }
        return null;
    }

    // Delete project by ID
    public boolean deleteProject(Long id) {
        projectRepository.deleteById(id);
        return !projectRepository.existsById(id);
    }
}
