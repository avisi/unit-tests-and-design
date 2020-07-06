package nl.avisi.demo.goodexample;

import org.springframework.stereotype.Component;

import nl.avisi.demo.rest.Person;
import nl.avisi.demo.rest.Person.Gender;

@Component
public class PersonMapper {
    nl.avisi.demo.model.Person fromRest(final Person restPerson) {
        final Person.Name name = restPerson.getName();
        return new nl.avisi.demo.model.Person(
                restPerson.getGender().equals(Gender.MALE),
                name.getFirst() + " " + name.getLast());
    }
}
