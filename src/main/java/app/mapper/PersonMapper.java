package app.mapper;

import app.model.Car;
import app.model.CarDTO;
import app.model.Person;
import app.model.PersonDTO;
import org.mapstruct.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Logger LOG = LoggerFactory.getLogger(PersonMapper.class);

    @Mappings({
            @Mapping(source = "address", target = "residence", defaultValue = "unknown"),
            @Mapping(target = "firstName", expression = "java(person.getFullName().split(\" \")[0])"),
            @Mapping(target = "lastName", expression = "java(person.getFullName().split(\" \")[1])"),
            @Mapping(source = "dateOfBirth", target = "born", dateFormat = "yyyy dd MM"),
            @Mapping(source = "numberOfVisits", target = "timesVisited"),
            @Mapping(source = "moneyOnHisMind", target = "money", numberFormat = "#.##E0")
    })
    PersonDTO personToPersonDTO(Person person);

    List<PersonDTO> personsToPersonDTOs(List<Person> persons);

    Set<PersonDTO> personsToPersonDTOs(Set<Person> persons);

    default CarDTO carToCarDTO(Car car) {
        return CarDTO.builder().modelOfCar(car.getCarModel()).build();
    }

    @InheritInverseConfiguration
    Person personDTOToPerson(PersonDTO personDTO);

    @BeforeMapping
    default void withArguments(Person person, @MappingTarget PersonDTO personDTO) {
        LOG.info("Mapping " + person + " to " + PersonDTO.class);
    }

    @AfterMapping
    default void doComplexMapping(Person person, @MappingTarget PersonDTO personDTO) {
        // do complex mapping
        LOG.info("Mapped " + person + " to " + personDTO);
    }

}
