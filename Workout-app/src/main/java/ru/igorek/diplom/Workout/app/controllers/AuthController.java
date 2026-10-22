package ru.igorek.diplom.Workout.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.igorek.diplom.Workout.app.models.Person;
import ru.igorek.diplom.Workout.app.services.RegistrationService;
import ru.igorek.diplom.Workout.app.util.PersonValidator;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;


    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> performRegistration(@RequestBody @Valid Person person,
                                                          BindingResult bindingResult){

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        registrationService.register(person);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
