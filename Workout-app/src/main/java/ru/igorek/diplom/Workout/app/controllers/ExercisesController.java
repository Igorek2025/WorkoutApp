package ru.igorek.diplom.Workout.app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.igorek.diplom.Workout.app.entity.ExercisesDb;
import ru.igorek.diplom.Workout.app.models.Exercises;
import ru.igorek.diplom.Workout.app.services.ExercisesService;
import ru.igorek.diplom.Workout.app.exeptions.ExercisesErrorResponse;
import ru.igorek.diplom.Workout.app.exeptions.ExercisesNotCreatedException;
import ru.igorek.diplom.Workout.app.exeptions.ExercisesNotFoundException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercises")
public class ExercisesController {
    private ExercisesService exercisesService;

    @Autowired
    public ExercisesController(ExercisesService exercisesService) {
        this.exercisesService = exercisesService;
    }

    @GetMapping("/all")
    public List<ExercisesDb> getAllExercises(){
        return exercisesService.findAll();
    }


    @PostMapping()
    public ResponseEntity<HttpStatus> create (@RequestBody ExercisesDb exercisesDb, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors){
                errorMsg.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");

            }
            throw new ExercisesNotCreatedException(errorMsg.toString());
        }
        exercisesService.save(exercisesDb);

        return ResponseEntity.ok(HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateExercises(@PathVariable UUID id, @RequestBody ExercisesDb exercisesDb){
        exercisesService.updateExercises(id, exercisesDb);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExercises(@PathVariable UUID id){
        exercisesService.deleteExercises(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
