package nl.avisi.demo.badexample;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.avisi.demo.UpstreamUnavailableException;

@AllArgsConstructor
public abstract class RandomUserBaseRepository {
    private final RestTemplate restTemplate;
    private final BaseConfig config;

    @Component
    @Data
    public final static class BaseConfig {
        @Value("${randomuser.me}")
        private final String baseUrl;
    }

    /**
     * @param <R> Type of expected response body, if status is 200
     * @param endpoint the last part of the url to request
     * @param type runtime representation of R
     * @return the body, deserialized as an R
     */
    protected <R> R get(//
            final String endpoint, //
            final ParameterizedTypeReference<R> type) {
        final var uri = URI.create(config.getBaseUrl() + endpoint);
        final var requestEntity = RequestEntity.get(uri).build();
        final var response = restTemplate.exchange(requestEntity, type);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new UpstreamUnavailableException(
                    "Responded with status " + response.getStatusCode());
        }
    }
}
