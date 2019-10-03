package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "visits")
public class Visit {


    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate visitDate;

    @NotEmpty
    @Column(name = "visit_description")
    @Pattern(regexp = "\\p{IsLatin}+")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Visit() {
    }

    public Visit(LocalDateTime visitDate) {
        this.visitDate = LocalDate.now();
    }

    public Visit(LocalDate visitDate, @NotEmpty @Pattern(regexp = "\\p{IsLatin}+") String description, Pet pet) {
        this.visitDate = visitDate;
        this.description = description;
        this.pet = pet;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return visitDate.equals(visit.visitDate) &&
                description.equals(visit.description) &&
                Objects.equals(pet, visit.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitDate, description, pet);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitDate=" + visitDate +
                ", description='" + description + '\'' +
                ", pet=" + pet +
                '}';
    }
}
