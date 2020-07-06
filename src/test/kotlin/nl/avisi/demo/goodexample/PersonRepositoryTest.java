package nl.avisi.demo.goodexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.avisi.demo.UpstreamUnavailableException;
import nl.avisi.demo.model.Person;
import nl.avisi.demo.rest.ResultContainer;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {
    @Test
    void happyFlow(final @Mock PersonMapper personMapper,
            final @Mock RandomUserRepositoryUtil repositoryUtil,
            final @Mock ResultContainer resultContainer,
            final @Mock nl.avisi.demo.rest.Person restPerson,
            final @Mock Person person) {
        final var sut = new PersonRepository(personMapper, repositoryUtil);
        Mockito.when(repositoryUtil.get(Mockito.anyString(), Mockito.any()))
                .thenReturn(resultContainer);
        Mockito.when(resultContainer.getResults())
                .thenReturn(Collections.singletonList(restPerson));
        Mockito.when(personMapper.fromRest(restPerson)).thenReturn(person);

        assertEquals(Collections.singletonList(person), sut.getAll());
    }

    @Test
    void unhappyFlow(final @Mock PersonMapper personMapper,
            final @Mock RandomUserRepositoryUtil repositoryUtil) {
        final var sut = new PersonRepository(personMapper, repositoryUtil);
        Mockito.when(repositoryUtil.get(Mockito.anyString(), Mockito.any()))
                .thenThrow(new UpstreamUnavailableException(""));

        assertThrows(UpstreamUnavailableException.class, () -> sut.getAll());
    }
}
