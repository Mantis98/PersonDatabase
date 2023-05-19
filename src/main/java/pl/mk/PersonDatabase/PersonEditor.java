package pl.mk.PersonDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonEditor {

    @Autowired
    PersonDatabaseRepository personDatabaseRepository;

    @GetMapping("/test")
    public String test() {
        return "work in progress";
    }

    @GetMapping("")
    public List<Person> getAll() {
        return personDatabaseRepository.getAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable("id") int id) {
        return personDatabaseRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Person> persons) {
        return personDatabaseRepository.save(persons);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Person updatedPerson) {
        Person person = personDatabaseRepository.getById(id);

        if (person != null) {
            person.setName(updatedPerson.getName());
            person.setLastName(updatedPerson.getLastName());
            person.setAge(updatedPerson.getAge());

            personDatabaseRepository.update(person);

            return 1;
        } else {
            return 1;
        }
    }


    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return personDatabaseRepository.delete(id);
    }

}

