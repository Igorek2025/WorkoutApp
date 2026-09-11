package ru.igorek.diplom.Workout.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.igorek.diplom.Workout.app.models.Exercises;
import ru.igorek.diplom.Workout.app.repositories.ExercisesRepository;
import ru.igorek.diplom.Workout.app.util.ExercisesNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ExercisesService {
    private ExercisesRepository exercisesRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ExercisesService(ExercisesRepository exercisesRepository, ModelMapper modelMapper) {
        this.exercisesRepository = exercisesRepository;
        this.modelMapper = modelMapper;
    }

    public List<Exercises> findAll(){
        return exercisesRepository.findAll();
    }

    @Transactional
    public void save(Exercises exercises) {
        exercisesRepository.save(exercises);
    }

    @Transactional
    public void updateExercises(UUID id, Exercises Newexercises) {
        Exercises exercises = exercisesRepository.findById(id).orElseThrow(ExercisesNotFoundException::new);
        modelMapper.map(Newexercises, exercises);
    }

    @Transactional
    public void deleteExercises(UUID id) {
        exercisesRepository.deleteById(id);
    }
}
