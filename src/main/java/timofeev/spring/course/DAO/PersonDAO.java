package timofeev.spring.course.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import timofeev.spring.course.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, date_of_birth) VALUES(?, ?)", person.getFIO(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, date_of_birth=? WHERE person_id=?", updatedPerson.getFIO(),
                updatedPerson.getYearOfBirth(), id);
    }

    public Person getPersonByFIO(String FIO){
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{FIO}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE person_id=?", null, id);
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}
