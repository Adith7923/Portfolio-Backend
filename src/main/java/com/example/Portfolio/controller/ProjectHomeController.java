package com.example.Portfolio.controller;

import com.example.Portfolio.model.ProjectHome;
import com.example.Portfolio.service.ProjectHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

@RestController
@RequestMapping("/project-home")
public class ProjectHomeController {

    @Autowired
    private ProjectHomeService service;

    // 🔓 Public - Anyone can view project home details
    @GetMapping
    public List<ProjectHome> getAllProjects() {
        return service.getAllProjects();
    }

    // 🔓 Public - Anyone can view a specific project
    @GetMapping("/{id}")
    public ResponseEntity<ProjectHome> getProjectById(@PathVariable Long id) {
        return service.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔒 Admin Only - Create a new project
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProjectHome> createProject(@RequestParam("title") String title,
                                                     @RequestParam("description") String description,
                                                     @RequestParam("image") MultipartFile image,
                                                     @RequestParam("link") String link) {
        try {
            Blob blob = new SerialBlob(image.getBytes());
            ProjectHome project = new ProjectHome();
            project.setTitle(title);
            project.setDescription(description);
            project.setImage(blob);
            project.setLink(link);
            return ResponseEntity.ok(service.saveProject(project));
        } catch (IOException | SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    // 🔒 Admin Only - Delete a project
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
