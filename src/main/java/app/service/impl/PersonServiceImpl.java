package app.service.impl;

import app.model.Person;
import app.service.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Person getRandomPerson() {
        return Person.builder()
                .fullName("Laydown Relax")
                .address("Wullford Road 23")
                .dateOfBirth(LocalDate.of(1988, 5, 8))
                .build();
    }
}
