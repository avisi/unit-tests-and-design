package nl.avisi.demo.rest;

import java.util.List;

import lombok.Data;

@Data
public class ResultContainer {
    private final List<Person> results;
}
