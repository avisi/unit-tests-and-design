package nl.avisi.demo.badexample;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import nl.avisi.demo.UpstreamUnavailableException;
import nl.avisi.demo.model.Person;
import nl.avisi.demo.rest.ResultContainer;

/**
 * BadPersonRepository illustrates how using static methods and extending other
 * classes lead to highly coupled code.
 * 
 * Note that there is no way to redirect the
 * {@code RandomUserBaseRepository.get} or the {@code PersonMapper.fromRest}
 * call. Meaning that in a test, we cannot cut those two implementations out of
 * the scope of our test.
 * 
 * This will make our unit tests more brittle> Our tests are more likely to
 * break if classes other than the one we are testing, change.
 */
@Slf4j
@Repository("badExamplePersonRepository")
public class PersonRepository extends RandomUserBaseRepository {
    public PersonRepository(final RestTemplate restTemplate,
            final BaseConfig config) {
        super(restTemplate, config);
    }

    public List<Person> getAll() {
        final var type = new ParameterizedTypeReference<ResultContainer>() {
        };
        try {
            return get("/api", type) //
                    .getResults() //
                    .stream() //
                    .map(PersonMapper::fromRest) //
                    .collect(Collectors.toList());
        } catch (UpstreamUnavailableException e) {
            log.error("Unable to getAll()", e);
            throw e;
        }
    }
}
