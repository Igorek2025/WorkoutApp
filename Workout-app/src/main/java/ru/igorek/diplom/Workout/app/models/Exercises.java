package ru.igorek.diplom.Workout.app.models;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercises {
    private String name;
    private String musclegroup;
    private String description;
    private String linkVideo;
    private Person owner;
}
