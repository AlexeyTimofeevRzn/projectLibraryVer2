package timofeev.spring.course.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import timofeev.spring.course.models.Book;
import timofeev.spring.course.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    private final PersonDAO personDAO;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.personDAO = personDAO;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id}, new BookMapper()).
                stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(name, author, date_of_creation) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYearOfCreate());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book SET name=?, author=?, date_of_creation=? WHERE book_id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYearOfCreate(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public List<Book> getPersonBooks(int id){
        return jdbcTemplate.query("SELECT * FROM book where person_id=" + id, new BookMapper());
    }

    public void setBookForPerson(int personId, int bookId){
        jdbcTemplate.update("UPDATE book SET person_id=? where book_id=?", personId, bookId);
    }

//    public int getPersonId(int bookId){
//        return jdbcTemplate.query("SELECT * FROM BOOK WHERE book_id=" + bookId,
//                new BookMapper()).stream().findAny().orElse(null).getOwnerId();
//    }

    public Optional<Person> getBookOwner(int id) {
        // Выбираем все колонки таблицы Person из объединенной таблицы
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id = person.person_id " +
                        "WHERE book_id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny();
    }

    public void release(int bookId){
        jdbcTemplate.update("UPDATE book SET person_id=? where book_id=?", null, bookId);
    }
}
