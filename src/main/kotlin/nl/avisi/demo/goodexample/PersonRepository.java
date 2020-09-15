package nl.avisi.demo.goodexample;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.avisi.demo.UpstreamUnavailableException;
import nl.avisi.demo.model.Person;
import nl.avisi.demo.rest.ResultContainer;

@Slf4j
@Repository
@AllArgsConstructor
public class PersonRepository {
    private final PersonMapper mapper;
    private final RandomUserRepositoryUtil repositoryUtil;
    

    private static final ParameterizedTypeReference<ResultContainer> type = new ParameterizedTypeReference<ResultContainer>() {
    };

    public List<Person> getAll() {
        try {
            return repositoryUtil.get("/api", type) //
                    .getResults() //
                    .stream() //
                    .map(mapper::fromRest) //
                    .collect(Collectors.toList());
        } catch (UpstreamUnavailableException e) {
            log.error("Unable to getAll()", e);
            throw e;
        }
    }
}
