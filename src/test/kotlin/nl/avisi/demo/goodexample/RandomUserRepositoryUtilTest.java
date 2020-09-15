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
    void checkGetWithStatusOK(
            @Mock RestTemplate restTemplate,
            @Mock BaseConfig baseConfig,
            @Mock ParameterizedTypeReference<Object> type,
            @Mock ResponseEntity<Object> response,
            @Mock Object mockBody) {
        var sut = new RandomUserRepositoryUtil(restTemplate, baseConfig);
        Mockito.when(restTemplate.exchange(
                Mockito.<RequestEntity<Void>>any(),
                Mockito.<ParameterizedTypeReference<Object>>any()))
                .thenReturn(response);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.OK);
        Mockito.when(response.getBody()).thenReturn(mockBody);
        assertEquals(mockBody, sut.get("", type));
    }

    @Test
    void checkGetWithStatusServerError(
            @Mock RestTemplate restTemplate,
            @Mock BaseConfig baseConfig,
            @Mock ParameterizedTypeReference<Object> type,
            @Mock ResponseEntity<Object> response,
            @Mock Object mockBody) {
        var sut = new RandomUserRepositoryUtil(restTemplate, baseConfig);
        Mockito.when(restTemplate.exchange(
                Mockito.<RequestEntity<Void>>any(),
                Mockito.<ParameterizedTypeReference<Object>>any()))
                .thenReturn(response);
        Mockito.when(response.getStatusCode())
                .thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        assertThrows(UpstreamUnavailableException.class,
                () -> sut.get("", type));
    }
}
