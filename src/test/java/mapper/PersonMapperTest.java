package mapper;

import app.Application;
import app.mapper.PersonMapper;
import app.model.Car;
import app.model.Person;
import app.model.PersonDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonMapperTest {

    private Person person_hightop;

    @Autowired
    private PersonMapper personMapper;

    @Before
    public void init() {
        person_hightop = Person.builder()
                .fullName("Hightop Straw")
                .address("Pennbroke 82")
                .dateOfBirth(LocalDate.of(1989, 10, 13))
                .car(Car.builder().carModel("\"Renault Laguna\"").build())
                .build();
    }

    @Test
    public void testAddressMapping() {

        final PersonDTO mapped = personMapper.personToPersonDTO(person_hightop);
        assertThat(mapped.getResidence()).isEqualTo(person_hightop.getAddress());

    }

    @Test
    public void testAddressDefaultMapping() {
        person_hightop.setAddress(null);
        final PersonDTO mapped = personMapper.personToPersonDTO(person_hightop);
        assertThat(mapped.getResidence()).isEqualTo("unknown");
    }

    @Test
    public void testName() {
        final PersonDTO mapped = personMapper.personToPersonDTO(person_hightop);
        assertThat(mapped.getFirstName()).isEqualTo("Hightop");
        assertThat(mapped.getLastName()).isEqualTo("Straw");
    }

    @Test
    public void testYearOfBirth() {
        final PersonDTO mapped = personMapper.personToPersonDTO(person_hightop);
        assertThat(mapped.getYearOfBirth()).isEqualTo("1989");
    }

}
