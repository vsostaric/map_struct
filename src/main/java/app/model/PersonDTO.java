package app.model;

import lombok.Data;

@Data
public class PersonDTO {

    private String firstName;
    private String lastName;
    private String residence;
    private String yearOfBirth;

    private CarDTO carDTO;

}
