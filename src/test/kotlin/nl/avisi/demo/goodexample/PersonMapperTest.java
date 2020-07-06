package nl.avisi.demo.goodexample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.avisi.demo.rest.Person;
import nl.avisi.demo.rest.Person.Gender;
import nl.avisi.demo.rest.Person.Name;

@ExtendWith(MockitoExtension.class)
public class PersonMapperTest {
    @Test
    void checkFromRest() {
        final var sut = new PersonMapper();
        final var input =
                new Person(Gender.MALE, new Name("title", "first", "last"));
        final var output = new nl.avisi.demo.model.Person(true, "first last");
        assertEquals(output, sut.fromRest(input));
    }
}
