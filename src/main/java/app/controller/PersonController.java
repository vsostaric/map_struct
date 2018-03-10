package app.controller;

import app.mapper.PersonMapper;
import app.model.Person;
import app.model.PersonDTO;
import app.service.PersonService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getPerson")
    public Person getPerson() {
        return personService.getRandomPerson();
    }


    @GetMapping("/getPersonDTO")
    public PersonDTO getPersonDTO() {
        return PersonMapper.INSTANCE.personToPersonDTO(personService.getRandomPerson());
    }

}
