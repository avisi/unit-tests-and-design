package nl.avisi.demo.badexample;

import nl.avisi.demo.rest.Person;
import nl.avisi.demo.rest.Person.Gender;

interface PersonMapper {
    static nl.avisi.demo.model.Person fromRest(final Person restPerson) {
        final var name = restPerson.getName();
        return new nl.avisi.demo.model.Person(//
                restPerson.getGender().equals(Gender.MALE), //
                name.getFirst() + " " + name.getLast());
    }
}
