package ru.igorek.diplom.Workout.app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.igorek.diplom.Workout.app.dto.ExercisesDTO;
import ru.igorek.diplom.Workout.app.models.Exercises;
import ru.igorek.diplom.Workout.app.services.ExercisesService;
import ru.igorek.diplom.Workout.app.util.ExercisesErrorResponse;
import ru.igorek.diplom.Workout.app.util.ExercisesNotCreatedException;
import ru.igorek.diplom.Workout.app.util.ExercisesNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercises")
public class ExercisesController {
    private ExercisesService exercisesService;
    private final ModelMapper modelMapper;

    @Autowired
    public ExercisesController(ExercisesService exercisesService, ModelMapper modelMapper) {
        this.exercisesService = exercisesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<ExercisesDTO> getAllExercises(){
        return exercisesService.findAll().stream().map(this::convertToExercisesDTO).collect(Collectors.toList());
    }


    @PostMapping()
    public ResponseEntity<HttpStatus> create (@RequestBody ExercisesDTO exercisesDTO, BindingResult bindingResult){
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
        exercisesService.save(convertToExercises(exercisesDTO));

        return ResponseEntity.ok(HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateExercises(@PathVariable UUID id, @RequestBody ExercisesDTO exercisesDTO){
        exercisesService.updateExercises(id, convertToExercises(exercisesDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExercises(@PathVariable UUID id){
        exercisesService.deleteExercises(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    @ExceptionHandler
    private ResponseEntity<ExercisesErrorResponse> handleException(ExercisesNotFoundException e){
        ExercisesErrorResponse response = new ExercisesErrorResponse(
                "Person with this id wasnt found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ExercisesErrorResponse> handleException(ExercisesNotCreatedException e){
        ExercisesErrorResponse response = new ExercisesErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ExercisesDTO convertToExercisesDTO(Exercises exercises){
        return modelMapper.map(exercises, ExercisesDTO.class);
    }
    private Exercises convertToExercises(ExercisesDTO exercisesDTO){
        return modelMapper.map(exercisesDTO, Exercises.class);
    }

}
