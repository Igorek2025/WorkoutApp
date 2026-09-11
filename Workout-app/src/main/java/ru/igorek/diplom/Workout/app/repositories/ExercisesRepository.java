package ru.igorek.diplom.Workout.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igorek.diplom.Workout.app.models.Exercises;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExercisesRepository extends JpaRepository<Exercises, UUID> {

}
