package nl.avisi.demo.goodexample;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;

@Service
@AllArgsConstructor
public class PersonService {
    private final @Delegate PersonRepository repository;
}
