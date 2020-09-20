package nl.avisi.demo.goodexample;

import nl.avisi.demo.rest.Person;

interface PersonMapper {
    nl.avisi.demo.model.Person fromRest(Person restPerson);
}
