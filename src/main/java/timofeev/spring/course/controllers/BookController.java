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
import timofeev.spring.course.services.BookService;
import timofeev.spring.course.services.PeopleService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

//    private final BookDAO bookDAO;
//    private final PersonDAO personDAO;

    private final PeopleService peopleService;

    private final BookService bookService;

    @Autowired
    public BookController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
//        this.bookDAO = bookDAO;
//        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookService.index());

        return "books/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("thisBook", bookService.show(id));

        Person owner = bookService.getBookOwner(id);

        if (owner != null){
            model.addAttribute("owner", owner);
        }
        else{
            model.addAttribute("people", peopleService.index());
        }

        return "books/show";
    }

    @PatchMapping("/{id}/select")
    public String setBookForPerson(@PathVariable("id") int bookId, @ModelAttribute("person")Person person){
        bookService.setBookForPerson(person.getId(), bookId);

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

        bookService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("thisBook", bookService.show(id));

        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("thisBook") @Valid Book book, @PathVariable("id") int id,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/books/edit";
        }

        bookService.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.release(id);

        return "redirect:/books/" + id;
    }

    @GetMapping("/page/{page}")
    public String getPage(@PathVariable("page") int page, Model model){
        model.addAttribute("books", bookService.getPage(page, 3));
        model.addAttribute("page", page);

        return "/books/showPage";
    }

    @GetMapping("/page/directTo/{page}")
    public String getNextPage(@PathVariable("page") int page, Model model){
        model.addAttribute("books", bookService.getPage(page + 1, 3));
        model.addAttribute("page", page + 1);

        return "/books/showPage";
    }
}
