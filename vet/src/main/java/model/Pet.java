package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;
import rest.PetDeserializer;
import rest.PetSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "pets")
@JsonSerialize(using = PetSerializer.class)
@JsonDeserialize(using = PetDeserializer.class)
public class Pet extends BaseEntity {

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @ManyToOne(targetEntity = PetSpecies.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private PetSpecies petSpecies;

    @ManyToOne
    @JoinColumn( name = "client_id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Visit.class)
    private List<Visit> visits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = HomeVisit.class)
    private List<HomeVisit> homeVisits;

    public Pet() {
    }

    public Pet(LocalDate birthDate, PetSpecies petSpecies, Client client) {
        this.birthDate = birthDate;
        this.petSpecies = petSpecies;
        this.client = client;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PetSpecies getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(PetSpecies petSpecies) {
        this.petSpecies = petSpecies;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<HomeVisit> getHomeVisits() {
        return homeVisits;
    }

    public void setHomeVisits(List<HomeVisit> homeVisits) {
        this.homeVisits = homeVisits;
    }

    @JsonIgnore
    protected List<Visit> getVisitsInternally(){

        if (this.visits == null || visits.isEmpty()){

            this.visits = new ArrayList<>();
        }
        return  this.visits;
    }

    @JsonIgnore
    protected List<HomeVisit> getHomeVisitsInternally(){

        if (this.homeVisits == null || homeVisits.isEmpty()){

            this.homeVisits = new ArrayList<>();
        }

        return homeVisits;
    }

    public List<Visit> getAllVisits(){

        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternally());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public List<HomeVisit> getAllHomeVisits(){

        List<HomeVisit> sortedHomeVisits = new ArrayList<HomeVisit>(getHomeVisitsInternally());
        PropertyComparator.sort(sortedHomeVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedHomeVisits);
    }

    public void addVisit(Visit visit){

        getAllVisits().add(visit);
        visit.setPet(this);
    }

    public void addHomeVisit(HomeVisit homeVisit){

        getAllHomeVisits().add(homeVisit);
        homeVisit.setPet(this);
    }
}
