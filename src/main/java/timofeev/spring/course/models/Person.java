package timofeev.spring.course.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {

    private int id;

    @NotEmpty(message = "FIO shouldn`t be empty")
    @Size(min = 3, max = 50, message = "FIO should be between 3 and 50 chars")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+",
            message = "FIO should be like this: Alishev Neil Valerievich")
    private String FIO;

    @Min(value = 1900, message = "YearOfBirth shoud be greater than 1900")
    private int yearOfBirth;

    public Person(){

    }

    public Person(int id, String FIO, int yearOfBirth){
        this.id = id;
        this.FIO = FIO;
        this.yearOfBirth = yearOfBirth;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }


    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
