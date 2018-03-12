package app.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Person {

    private String fullName;
    private String address;
    private LocalDate dateOfBirth;
    private Car car;

}
