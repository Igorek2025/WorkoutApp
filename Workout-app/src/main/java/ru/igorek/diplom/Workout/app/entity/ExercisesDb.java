package ru.igorek.diplom.Workout.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import ru.igorek.diplom.Workout.app.models.Person;

import java.util.UUID;

@Entity
@Table(name = "exercises")
public class ExercisesDb {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "musclegroup")
    @NotEmpty
    private String muscleGroup;

    @Column(name = "description")
    private String description;

    @Column(name = "linkvideo")
    private String linkVideo;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

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

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
