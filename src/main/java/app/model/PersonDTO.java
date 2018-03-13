package app.model;

import lombok.Data;

@Data
public class PersonDTO {

    private String firstName;
    private String lastName;
    private String residence;
    private String born;
    private Long timesVisited;
    private String money;
    private CarDTO carDTO;

}
