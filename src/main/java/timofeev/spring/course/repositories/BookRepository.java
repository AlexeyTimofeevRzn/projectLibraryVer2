package timofeev.spring.course.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timofeev.spring.course.models.Book;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> findBookByOwnerId(int id);

    public Optional<Book> findByName(String name);

    public List<Book> findAllByOrderByYearOfCreateAsc();

    public List<Book> findByNameStartingWith(String start);
}
