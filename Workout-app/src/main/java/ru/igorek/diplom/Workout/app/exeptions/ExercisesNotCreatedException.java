package ru.igorek.diplom.Workout.app.exeptions;

public class ExercisesNotCreatedException extends RuntimeException {
    public ExercisesNotCreatedException(String message) {
        super(message);
    }
}
