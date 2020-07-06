package nl.avisi.demo.goodexample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.avisi.demo.model.Person;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Test
    void checkGetAllDelegates(//
            final @Mock PersonRepository personRepository,
            final @Mock List<Person> mockPersonList) {
        final var sut = new PersonService(personRepository);
        Mockito.when(personRepository.getAll()).thenReturn(mockPersonList);
        assertEquals(mockPersonList, sut.getAll());
    }
}
