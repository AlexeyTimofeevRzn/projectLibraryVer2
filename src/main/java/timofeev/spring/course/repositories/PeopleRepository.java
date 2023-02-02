package timofeev.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timofeev.spring.course.models.Person;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    public List<Person> findByFIO(String FIO);

    public List<Person> findByFIOOrderByYearOfBirth(String name);
}
