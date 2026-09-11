package ru.igorek.diplom.Workout.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "exercises")
public class Exercises {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    @NotEmpty(message = "Name should be empty")
    @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "musclegroup")
    @NotEmpty(message = "Musclegroup should be empty")
    private String musclegroup;

    @Column(name = "description")
    private String description;

    @Column(name = "linkvideo")
    private String linkVideo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusclegroup() {
        return musclegroup;
    }

    public void setMusclegroup(String musclegroup) {
        this.musclegroup = musclegroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }
}
