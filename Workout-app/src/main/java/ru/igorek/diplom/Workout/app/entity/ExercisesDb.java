package ru.igorek.diplom.Workout.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "exercises")
public class ExercisesDb {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    @NotEmpty(message = "Name should be empty")
    private String name;

    @Column(name = "musclegroup")
    @NotEmpty
    private String musclegroup;

    @Column(name = "description")
    private String description;

    @Column(name = "linkvideo")
    private String linkVideo;
}
