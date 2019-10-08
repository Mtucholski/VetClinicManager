package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import rest.HomeVisitDeserializer;
import rest.HomeVisitSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "homeVisits")
@JsonSerialize(using = HomeVisitSerializer.class)
@JsonDeserialize(using = HomeVisitDeserializer.class)
public class HomeVisit extends Visit {


    @Column(name="veterinarian")
    @NotEmpty
    private Vet vet;

    @Column
    @NotEmpty
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime dateFrom;

    @Column
    @NotEmpty
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy/HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy/HH:mm")
    private LocalDateTime dateTo;

    public HomeVisit() {
        super();
    }

    public HomeVisit(Vet vet, @NotEmpty LocalDateTime dateFrom, @NotEmpty LocalDateTime dateTo) {
        this.vet = vet;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public HomeVisit(LocalDateTime visitDate, Vet vet, @NotEmpty LocalDateTime dateFrom, @NotEmpty LocalDateTime dateTo) {

        super(visitDate);
        this.vet = vet;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public HomeVisit(LocalDate visitDate, @NotEmpty @Pattern(regexp = "\\p{IsLatin}+") String description, Pet pet, Vet vet, @NotEmpty LocalDateTime dateFrom, @NotEmpty LocalDateTime dateTo) {
        super(visitDate, description, pet);
        this.vet = vet;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }
}
