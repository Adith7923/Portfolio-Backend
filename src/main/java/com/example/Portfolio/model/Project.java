package com.example.Portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.ElementCollection; // For storing lists in the database
import jakarta.persistence.CollectionTable; // To customize the table used for the collection
import jakarta.persistence.Column;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Lob
    @Column(length = 100000)
    private String description;
    private String reportLink;

    @ElementCollection
    @CollectionTable(name = "project_technologies") // Custom table name for technologies
    @Column(name = "technology")
    private List<String> technologiesUsed; // List to store technologies

    @ElementCollection
    @CollectionTable(name = "project_objectives") // Custom table name for objectives
    @Column(name = "objective")
    private List<String> objectives; // List to store project objectives

    @Lob
    private byte[] image; // Store image as a byte array

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportLink() {
        return reportLink;
    }

    public void setReportLink(String reportLink) {
        this.reportLink = reportLink;
    }

    public List<String> getTechnologiesUsed() {
        return technologiesUsed;
    }

    public void setTechnologiesUsed(List<String> technologiesUsed) {
        this.technologiesUsed = technologiesUsed;
    }

    public List<String> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<String> objectives) {
        this.objectives = objectives;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
