package timofeev.spring.course.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {

    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    private String name;

    @NotEmpty(message = "Author name shouldn't be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+",
            message = " Author FIO should be like this: Alishev Neil Valerievich")
    private String author;

    private int ownerId;

    private int yearOfCreate;

    public Book(){

    }

    public Book(int id, String name, String author, int ownerId, int yearOfCreate){
        this.author = author;
        this.id = id;
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
