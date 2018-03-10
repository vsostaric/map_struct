package app.mapper;

import app.model.Car;
import app.model.CarDTO;
import app.model.Person;
import app.model.PersonDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mappings({
            @Mapping(source = "address", target = "residence", defaultValue = "unknown"),
            @Mapping(target = "firstName", expression = "java(person.getFullName().split(\" \")[0])"),
            @Mapping(target = "lastName", expression = "java(person.getFullName().split(\" \")[1])"),
            @Mapping(source = "dateOfBirth", target = "yearOfBirth", dateFormat = "yyyy")
    })
    PersonDTO personToPersonDTO(Person person);

//    @InheritInverseConfiguration
//    Person personDTOToPerson(PersonDTO personDTO);

    default CarDTO carToCarDTO(Car car) {
        return CarDTO.builder().modelOfCar(car.getCarModel()).build();
    }

}
