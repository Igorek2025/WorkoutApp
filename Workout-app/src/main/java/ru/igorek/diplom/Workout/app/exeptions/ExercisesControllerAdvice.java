package ru.igorek.diplom.Workout.app.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class ExercisesControllerAdvice {
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

}
