package timofeev.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import timofeev.spring.course.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
