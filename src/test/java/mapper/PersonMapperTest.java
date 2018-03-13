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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonMapperTest {

    private Person person_hightop;
    private Person person_laydown;
    private Person person_run;

    private List<Person> personList;
    private Set<Person> personSet;

    private PersonDTO person_hightop_mapped;

    @Autowired
    private PersonMapper personMapper;

    @Before
    public void init() {
        person_hightop = Person.builder()
                .fullName("Hightop Straw")
                .address("Pennbroke 82")
                .dateOfBirth(LocalDate.of(1989, 10, 13))
                .numberOfVisits(7)
                .car(Car.builder().carModel("\"Renault Laguna\"").build())
                .build();

        person_laydown = Person.builder()
                .fullName("Laydown Relax")
                .address("Wullford Road 23")
                .dateOfBirth(LocalDate.of(1988, 5, 8))
                .numberOfVisits(23)
                .build();

        person_run = Person.builder()
                .fullName("Run Vigorously")
                .address("Someville 7")
                .dateOfBirth(LocalDate.of(1961, 1, 24))
                .numberOfVisits(115)
                .build();

        person_hightop_mapped = personMapper.personToPersonDTO(person_hightop);
    }

    @Test
    public void testAddressMapping() {
        assertThat(person_hightop_mapped.getResidence()).isEqualTo(person_hightop.getAddress());
    }

    @Test
    public void testAddressDefaultMapping() {
        person_hightop.setAddress(null);
        final PersonDTO mapped = personMapper.personToPersonDTO(person_hightop);
        assertThat(mapped.getResidence()).isEqualTo("unknown");
    }

    @Test
    public void testName() {
        assertThat(person_hightop_mapped.getFirstName()).isEqualTo("Hightop");
        assertThat(person_hightop_mapped.getLastName()).isEqualTo("Straw");
    }

    @Test
    public void testYearOfBirth() {
        assertThat(person_hightop_mapped.getBorn()).isEqualTo("1989 13 10");
    }

    @Test
    public void testIntToLongAndBack() {
        assertThat(person_hightop_mapped.getTimesVisited()).isEqualTo(7);

        final Person reMapped = personMapper.personDTOToPerson(person_hightop_mapped);
        assertThat(reMapped.getNumberOfVisits()).isEqualTo(7);
    }

    @Test
    public void testListMapping() {
        personList = new ArrayList<>();
        personList.add(person_hightop);
        personList.add(person_laydown);
        personList.add(person_run);

        final List<PersonDTO> mapped = personMapper.personsToPersonDTOs(personList);

        assertThat(mapped.size()).isEqualTo(3);
        assertThat(mapped.get(0).getFirstName()).isEqualTo("Hightop");
        assertThat(mapped.get(1).getFirstName()).isEqualTo("Laydown");
        assertThat(mapped.get(2).getFirstName()).isEqualTo("Run");
    }

    @Test
    public void testSetMapping() {
        personSet = new HashSet<>();
        personSet.add(person_hightop);
        personSet.add(person_laydown);
        personSet.add(person_run);

        final Set<PersonDTO> mapped = personMapper.personsToPersonDTOs(personSet);
        assertThat(mapped.size()).isEqualTo(3);

    }

}
