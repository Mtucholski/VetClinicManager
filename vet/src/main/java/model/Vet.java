package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

/**
 * java bean representing a veterinarian.
 * @author mateusztucholski
 */
@Entity
@Table(name = "veterinarians")
public class Vet extends Person {


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
    inverseJoinColumns = @JoinColumn(name = "vet_speciality_id"))
    private Set<VetSpeciality> specialties;

    @JsonIgnore
    protected Set<VetSpeciality> getSpecialtiesInternal(){

        if (this.specialties == null){

            this.specialties = new HashSet<>();
        }

        return this.specialties;
    }

    protected void setSpecialtiesInternal(Set<VetSpeciality> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public List<VetSpeciality> getSpecialties(){

        List<VetSpeciality> sortedList = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedList, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedList);
    }

    public void addSpecialty(VetSpeciality vetSpeciality){

        getSpecialtiesInternal().add(vetSpeciality);
    }

    public void removeSpecialty(VetSpeciality speciality){

       Optional<VetSpeciality> specialty = getSpecialtiesInternal().stream().filter(a -> a.getVetSpecialtyName().equals(speciality.getVetSpecialtyName())).findFirst();

       if (specialty.isPresent()){

           getSpecialtiesInternal().remove(specialty);
       }
    }
}
