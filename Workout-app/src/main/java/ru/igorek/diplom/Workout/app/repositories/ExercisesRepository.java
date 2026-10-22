package ru.igorek.diplom.Workout.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igorek.diplom.Workout.app.entity.ExercisesDb;
import ru.igorek.diplom.Workout.app.models.Person;


import java.util.List;
import java.util.UUID;

@Repository
public interface ExercisesRepository extends JpaRepository<ExercisesDb, UUID> {
    // Ищет общие упражнения + личные упражнения конкретного пользователя
    List<ExercisesDb> findByOwnerIsNullOrOwner(Person owner);


}
