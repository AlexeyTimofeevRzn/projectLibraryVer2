package timofeev.spring.course.models;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author name shouldn't be empty")
    @Column(name = "author")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+",
            message = " Author FIO should be like this: Alishev Neil")
    private String author;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @Column(name = "date_of_creation")
    private int yearOfCreate;

    public Book(){

    }

    public Book(int id, String name, String author, Person owner, int yearOfCreate){
        this.author = author;
        this.id = id;
        this.owner = owner;
        this.yearOfCreate = yearOfCreate;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfCreate() {
        return yearOfCreate;
    }

    public void setYearOfCreate(int yearOfCreate) {
        this.yearOfCreate = yearOfCreate;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
