package timofeev.spring.course.DAO;

import org.springframework.jdbc.core.RowMapper;
import timofeev.spring.course.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("book_id"));
        book.setName(rs.getString("name"));
        book.setYearOfCreate(rs.getInt("date_of_creation"));
        book.setAuthor(rs.getString("author"));
        book.setOwnerId(rs.getInt("person_id"));

        return book;
    }

}
