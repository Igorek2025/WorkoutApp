package ru.igorek.diplom.Workout.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.igorek.diplom.Workout.app.entity.ExercisesDb;
import ru.igorek.diplom.Workout.app.models.Exercises;
import ru.igorek.diplom.Workout.app.repositories.ExercisesRepository;
import ru.igorek.diplom.Workout.app.exeptions.ExercisesNotFoundException;

import java.util.List;
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

    public List<ExercisesDb> findAll(){
        return exercisesRepository.findAll();
    }

    public ExercisesDb findById(UUID id){
        return  exercisesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(ExercisesDb exercisesDb) {
        exercisesRepository.save(exercisesDb);
    }

    @Transactional
    public void updateExercises(UUID id, ExercisesDb NewexercisesDb) {
        ExercisesDb exercisesDb = exercisesRepository.findById(id).orElseThrow(ExercisesNotFoundException::new);
        modelMapper.map(NewexercisesDb, exercisesDb);
    }

    @Transactional
    public void deleteExercises(UUID id) {
        exercisesRepository.deleteById(id);
    }
}
