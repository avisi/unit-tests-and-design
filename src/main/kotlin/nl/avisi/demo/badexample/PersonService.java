package nl.avisi.demo.badexample;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@Service("badExamplePersonService")
@AllArgsConstructor
public class PersonService {
    private final @Delegate PersonRepository repository;
}
