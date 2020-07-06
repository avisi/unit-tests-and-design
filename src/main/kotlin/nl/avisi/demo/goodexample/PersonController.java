package nl.avisi.demo.goodexample;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import nl.avisi.demo.model.Person;

@RestController
@AllArgsConstructor
public class PersonController {
    /**
     * Person service to query people.
     */
    private final PersonService personService;

    @RequestMapping("/goodexample")
    public List<Person> getAll() {
        return personService.getAll();
    }
}
