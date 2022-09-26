package timofeev.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import timofeev.spring.course.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
