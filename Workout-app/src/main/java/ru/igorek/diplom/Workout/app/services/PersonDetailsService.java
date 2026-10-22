package ru.igorek.diplom.Workout.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.igorek.diplom.Workout.app.models.Person;
import ru.igorek.diplom.Workout.app.repositories.PeopleRepository;
import ru.igorek.diplom.Workout.app.security.PersonDetails;


import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Person> person = peopleRepository.findByUsername(s);

        if(person.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new PersonDetails(person.get());
    }
}
