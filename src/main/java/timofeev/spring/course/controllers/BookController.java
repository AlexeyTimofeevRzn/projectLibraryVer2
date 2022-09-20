package timofeev.spring.course.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import timofeev.spring.course.DAO.BookDAO;
import timofeev.spring.course.DAO.PersonDAO;
import timofeev.spring.course.models.Book;
import timofeev.spring.course.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());

        return "books/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("thisBook", bookDAO.show(id));

        Optional<Person> owner = bookDAO.getBookOwner(id);

        if (owner.isPresent()){
            model.addAttribute("owner", owner.get());
        }
        else{
            model.addAttribute("people", personDAO.index());
        }

        return "books/show";
    }

    @PatchMapping("/{id}/select")
    public String setBookForPerson(@PathVariable("id") int bookId, @ModelAttribute("person")Person person){
        bookDAO.setBookForPerson(person.getId(), bookId);

        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("thisBook", new Book());

        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("thisBook") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }

        bookDAO.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("thisBook", bookDAO.show(id));

        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("thisBook") Book book, @PathVariable("id") int id,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/books/edit";
        }

        bookDAO.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);

        return "redirect:/books/" + id;
    }
}
