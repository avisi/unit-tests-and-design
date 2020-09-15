package nl.avisi.demo.badexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import nl.avisi.demo.UpstreamUnavailableException;
import nl.avisi.demo.badexample.RandomUserBaseRepository.BaseConfig;
import nl.avisi.demo.rest.Person;
import nl.avisi.demo.rest.ResultContainer;
import nl.avisi.demo.rest.Person.Gender;
import nl.avisi.demo.rest.Person.Name;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @Test
    public void testGetAllHappyFlow(
            @Mock RestTemplate restTemplate,
            @Mock BaseConfig baseConfig,
            @Mock ResponseEntity<ResultContainer> response,
            @Mock ResultContainer resultContainer) {
        var sut = new PersonRepository(restTemplate, baseConfig);
        Mockito.when(restTemplate.exchange(
                Mockito.<RequestEntity<Void>>any(),
                Mockito.<ParameterizedTypeReference<ResultContainer>>any()))
                .thenReturn(response);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.OK);
        Mockito.when(response.getBody()).thenReturn(resultContainer);

        var list = Collections.singletonList(
                new Person(Gender.MALE, new Name("title", "first", "last")));

        Mockito.when(resultContainer.getResults()).thenReturn(list);
        var reference = Collections.singletonList(
                new nl.avisi.demo.model.Person(true, "first last"));

        assertEquals(reference, sut.getAll());
    }

    @Test
    public void testGetAllUnhappyFlow(
            @Mock RestTemplate restTemplate,
            @Mock BaseConfig baseConfig,
            @Mock ResponseEntity<ResultContainer> response,
            @Mock ResultContainer resultContainer) {
        var sut = new PersonRepository(restTemplate, baseConfig);
        Mockito.when(restTemplate.exchange(
                Mockito.<RequestEntity<Void>>any(),
                Mockito.<ParameterizedTypeReference<ResultContainer>>any()))
                .thenReturn(response);
        Mockito.when(response.getStatusCode())
                .thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        assertThrows(UpstreamUnavailableException.class, () -> sut.getAll());
    }
}
