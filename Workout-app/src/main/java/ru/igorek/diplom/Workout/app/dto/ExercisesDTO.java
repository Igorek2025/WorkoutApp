package ru.igorek.diplom.Workout.app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ExercisesDTO {
    @NotEmpty(message = "Name should be empty")
    @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Musclegroup should be empty")
    private String musclegroup;

    private String description;

    private String linkVideo;

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
