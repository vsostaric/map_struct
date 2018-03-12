package app.mapper;

import app.model.Car;
import app.model.CarDTO;
import app.model.Person;
import app.model.PersonDTO;
import org.mapstruct.*;

@Mapper
public interface PersonMapper {

    @Mappings({
            @Mapping(source = "address", target = "residence", defaultValue = "unknown"),
            @Mapping(target = "firstName", expression = "java(person.getFullName().split(\" \")[0])"),
            @Mapping(target = "lastName", expression = "java(person.getFullName().split(\" \")[1])"),
            @Mapping(source = "dateOfBirth", target = "born", dateFormat = "yyyy dd MM"),
            @Mapping(source = "numberOfVisits", target = "timesVisited")
    })
    PersonDTO personToPersonDTO(Person person);

    @InheritInverseConfiguration
    Person personDTOToPerson(PersonDTO personDTO);

    @AfterMapping
    default void doComplexMapping(Person person, @MappingTarget PersonDTO personDTO) {
        // do complex mapping
    }

    default CarDTO carToCarDTO(Car car) {
        return CarDTO.builder().modelOfCar(car.getCarModel()).build();
    }

}
