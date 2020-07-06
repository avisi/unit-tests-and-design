package nl.avisi.demo.badexample;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import nl.avisi.demo.model.Person;

@RestController("badExamplePersonController")
@AllArgsConstructor
public class PersonController {
    /**
     * Person service to query people.
     */
    private final PersonService personService;

    @RequestMapping("/badexample")
    public List<Person> getAll() {
        return personService.getAll();
    }
}
