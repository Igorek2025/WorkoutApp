package ru.igorek.diplom.Workout.app.controllers;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.igorek.diplom.Workout.app.entity.ExercisesDb;
import ru.igorek.diplom.Workout.app.models.Person;
import ru.igorek.diplom.Workout.app.repositories.ExercisesRepository;
import ru.igorek.diplom.Workout.app.security.PersonDetails;
import ru.igorek.diplom.Workout.app.services.ExercisesService;
import ru.igorek.diplom.Workout.app.exeptions.ExercisesNotCreatedException;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercises")
public class ExercisesController {
    private ExercisesService exercisesService;
    private final ExercisesRepository exercisesRepository;

    @Autowired
    public ExercisesController(ExercisesService exercisesService, ExercisesRepository exercisesRepository) {
        this.exercisesService = exercisesService;
        this.exercisesRepository = exercisesRepository;
    }

    @GetMapping("/all")
    public List<ExercisesDb> getAllExercises(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person currentUser = personDetails.getPerson();

        return exercisesRepository.findByOwnerIsNullOrOwner(currentUser);
    }


    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create (@Valid @RequestBody ExercisesDb exercisesDb, BindingResult bindingResult){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person currentUser = personDetails.getPerson();

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
        exercisesDb.setOwner(currentUser);
        exercisesService.save(exercisesDb);

        return ResponseEntity.ok(HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateExercises(@PathVariable UUID id, @RequestBody ExercisesDb exercisesDbNew){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person currentUser = personDetails.getPerson();

        ExercisesDb exercisesDb = exercisesService.findById(id);
        if(exercisesDb.getOwner() == null || exercisesDb.getOwner().getId()!=currentUser.getId()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        exercisesDbNew.setOwner(currentUser);

        exercisesService.updateExercises(id, exercisesDbNew);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExercises(@PathVariable UUID id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person currentUser = personDetails.getPerson();

        ExercisesDb exercisesDb = exercisesService.findById(id);
        if(exercisesDb.getOwner() == null || exercisesDb.getOwner().getId()!=currentUser.getId()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }



        exercisesService.deleteExercises(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }
}
