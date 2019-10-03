package model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * super class for user, vet, vet technician and client with basic fields (name, surname, birthday)
 */
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3, max = 20)
    @Pattern(regexp = "\\p{IsLatin}+")
    protected String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "last name cannot be empty")
    @Size(min = 3, max = 20)
    @Pattern(regexp = "\\p{IsLatin}+")
    protected String lastName;

    @Column(name = "personal_id_number")
    @NotEmpty(message = "cannot be empty")
    @Size(min = 11, max = 11)
    @Pattern(regexp = "[0-9]+")
    protected int personalID;

    @Column(name = "birth_day")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected LocalDate birthDay;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPersonalID() {
        return personalID;
    }

    public void setPersonalID(int personalID) {
        this.personalID = personalID;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
