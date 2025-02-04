package com.example.Portfolio.controller;

import com.example.Portfolio.model.Project;
import com.example.Portfolio.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // GET all projects (Public)
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // GET a project by ID (Public)
    @GetMapping("/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return project != null
                ? ResponseEntity.ok(project)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // GET a project by title (Public)
    @GetMapping("/title/{title}")
    public ResponseEntity<Project> getProjectByTitle(@PathVariable String title) {
        Optional<Project> project = projectService.getProjectByTitle(title);
        return project.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST a new project (Authenticated users only)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestParam("title") String title,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("extraDescription") String extraDescription,
                                                 @RequestParam("technologiesUsed") String technologiesUsed,
                                                 @RequestParam("objectives") String objectives,
                                                 @RequestParam("reportLink") String reportLink,
                                                 @RequestParam("image") MultipartFile image,
                                                 @RequestParam(value = "displayImage", required = false) MultipartFile displayImage) throws IOException {
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setExtraDescription(extraDescription);

        // Convert comma-separated strings into lists
        project.setTechnologiesUsed(Arrays.asList(technologiesUsed.split(",")));
        project.setObjectives(Arrays.asList(objectives.split(",")));

        project.setReportLink(reportLink);

        Project createdProject = projectService.createProject(project, image, displayImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // PUT to update an existing project (Authenticated users only)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("extraDescription") String extraDescription,
                                                 @RequestParam("technologiesUsed") String technologiesUsed,
                                                 @RequestParam("objectives") String objectives,
                                                 @RequestParam("reportLink") String reportLink,
                                                 @RequestParam("image") MultipartFile image,
                                                 @RequestParam(value = "displayImage", required = false) MultipartFile displayImage) throws IOException {
        Project projectDetails = new Project();
        projectDetails.setId(id);
        projectDetails.setTitle(title);
        projectDetails.setDescription(description);
        projectDetails.setExtraDescription(extraDescription);

        // Convert comma-separated strings into lists
        projectDetails.setTechnologiesUsed(Arrays.asList(technologiesUsed.split(",")));
        projectDetails.setObjectives(Arrays.asList(objectives.split(",")));

        projectDetails.setReportLink(reportLink);

        Project updatedProject = projectService.updateProject(id, projectDetails, image, displayImage);
        return updatedProject != null
                ? ResponseEntity.ok(updatedProject)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DELETE a project by ID (Authenticated users only)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        boolean isDeleted = projectService.deleteProject(id);
        return isDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
