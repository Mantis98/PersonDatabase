package pl.mk.PersonDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDatabaseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT id, name, lastname, age FROM person",
                BeanPropertyRowMapper.newInstance(Person.class));
    }

    public Person getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, lastname, age FROM person WHERE id=?",
                BeanPropertyRowMapper.newInstance(Person.class), id);
    }

    public int save(List<Person> persons) {
        persons.forEach(person -> jdbcTemplate.update("INSERT INTO person(name, lastname, age) VALUES(?, ?, ?)",
                person.getName(), person.getLastName(), person.getAge()));

        return 1;
    }

    public int update(Person person) {
        return jdbcTemplate.update("UPDATE person SET name=?, lastname=?, age=? WHERE id=?",
                person.getName(), person.getLastName(), person.getAge(), person.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

}
