package nl.avisi.demo.badexample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.avisi.demo.model.Person;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
    @Test
    public void testGetAll(//
            @Mock PersonService service, //
            @Mock List<Person> personList) {
        var sut = new PersonController(service);
        Mockito.when(service.getAll()).thenReturn(personList);
        assertEquals(personList, sut.getAll());
        Mockito.verify(service).getAll();
    }
}
