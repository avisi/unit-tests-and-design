package nl.avisi.demo.goodexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

@ExtendWith(MockitoExtension.class)
public class RandomUserRepositoryUtilTest {
    @Test
    void checkGetWithStatusOK(//
            final @Mock RestTemplate restTemplate,
            final @Mock BaseConfig baseConfig,
            final @Mock ParameterizedTypeReference<Object> type,
            final @Mock ResponseEntity<Object> response,
            final @Mock Object mockBody) {
        final var sut = new RandomUserRepositoryUtil(restTemplate, baseConfig);
        Mockito.when(restTemplate.exchange(
                // Specify generic types, to disambiguate (choose correct
                // overload) the overloaded exchange method.
                Mockito.<RequestEntity<Void>>any(),
                Mockito.<ParameterizedTypeReference<Object>>any()))
                .thenReturn(response);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.OK);
        Mockito.when(response.getBody()).thenReturn(mockBody);
        assertEquals(mockBody, sut.get("", type));
    }

    @Test
    void checkGetWithStatusServerError(//
            final @Mock RestTemplate restTemplate,
            final @Mock BaseConfig baseConfig,
            final @Mock ParameterizedTypeReference<Object> type,
            final @Mock ResponseEntity<Object> response,
            final @Mock Object mockBody) {
        final var sut = new RandomUserRepositoryUtil(restTemplate, baseConfig);
        Mockito.when(restTemplate.exchange(
                // Specify generic types, to disambiguate (choose correct
                // overload) the overloaded exchange method.
                Mockito.<RequestEntity<Void>>any(),
                Mockito.<ParameterizedTypeReference<Object>>any()))
                .thenReturn(response);
        Mockito.when(response.getStatusCode())
                .thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        assertThrows(UpstreamUnavailableException.class,
                () -> sut.get("", type));
    }
}
