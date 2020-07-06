package nl.avisi.demo.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Person {
    public enum Gender {
        @JsonProperty("male")
        MALE,
        @JsonProperty("female")
        FEMALE
    }

    private final Gender gender;

    @Data
    public static class Name {
        private final String title;
        private final String first;
        private final String last;
    }

    private final Name name;
}
