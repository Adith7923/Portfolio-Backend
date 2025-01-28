package com.example.Portfolio.service;

import com.example.Portfolio.model.ProjectHome;
import com.example.Portfolio.repository.ProjectHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectHomeService {

    @Autowired
    private ProjectHomeRepository repository;

    public List<ProjectHome> getAllProjects() {
        return repository.findAll();
    }

    public Optional<ProjectHome> getProjectById(Long id) {
        return repository.findById(id);
    }

    public ProjectHome saveProject(ProjectHome project) {
        return repository.save(project);
    }

    public void deleteProject(Long id) {
        repository.deleteById(id);
    }
}
