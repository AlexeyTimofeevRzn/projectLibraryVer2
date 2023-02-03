package timofeev.spring.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timofeev.spring.course.models.Book;
import timofeev.spring.course.models.Person;
import timofeev.spring.course.repositories.BookRepository;
import timofeev.spring.course.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    private final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> index(){
        return bookRepository.findAll();
    }

    public List<Book> indexSortByYear(){
        return bookRepository.findAllByOrderByYearOfCreateAsc();
    }

    public Book show(int id){ // В вызове в контроллере проверять на findAny
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        updatedBook.setOwner(getBookOwner(id));
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public List<Book> getPersonBooks(int ownerId){
        return bookRepository.findBookByOwnerId(ownerId);
    }

    @Transactional
    public void setBookForPerson(int personId, int bookId){
        Book book = show(bookId);
        book.setOwner(peopleRepository.findById(personId).orElse(null));
        bookRepository.save(book);
    }

    @Transactional
    public Person getBookOwner(int bookId){
//        if (bookRepository.findById(bookId).isPresent()){
//            return peopleRepository.findById(bookRepository.findById(bookId).orElse(null).getOwner().getId());
//        }
        return bookRepository.findById(bookId).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int bookId){
        Book book;
        book = bookRepository.findById(bookId).orElse(null);
        book.setOwner(null);
        bookRepository.save(book);
    }

    @Transactional
    public List<Book> getPage(int page, int itemsPerPage){
        return bookRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }

    public Book getBookByName(String name){
        return bookRepository.findByName(name).orElse(null);
    }

    public Book getBookStartingWithName(String name){
        return bookRepository.findByNameStartingWith(name).get(0);
    }
}
