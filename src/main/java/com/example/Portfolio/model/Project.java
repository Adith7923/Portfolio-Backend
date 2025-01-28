package com.example.Portfolio.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private String reportLink;

    @Column(columnDefinition = "TEXT") // Store technologiesUsed as JSON
    private String technologiesUsed;

    @Column(columnDefinition = "TEXT") // Store objectives as JSON
    private String objectives;

    @Lob
    private byte[] image; // Store image as a byte array

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Getters and Setters
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
        try {
            return objectMapper.readValue(technologiesUsed, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setTechnologiesUsed(List<String> technologiesUsed) {
        try {
            this.technologiesUsed = objectMapper.writeValueAsString(technologiesUsed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getObjectives() {
        try {
            return objectMapper.readValue(objectives, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setObjectives(List<String> objectives) {
        try {
            this.objectives = objectMapper.writeValueAsString(objectives);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
