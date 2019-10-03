package model;

import com.fasterxml.jackson.databind.ser.Serializers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "species")
public class PetSpecies extends BaseEntity {

    @Column(name= "species")
    @NotEmpty
    @Size (min = 3, max = 30)
    @Pattern(regexp = "\\p{IsLatin}+")
    private String species;

    public PetSpecies() {
    }

    public PetSpecies(@NotEmpty @Size(min = 3, max = 30) @Pattern(regexp = "\\p{IsLatin}+") String species) {
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetSpecies that = (PetSpecies) o;
        return Objects.equals(species, that.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species);
    }

    @Override
    public String toString() {
        return "PetSpecies{" +
                "species='" + species + '\'' +
                '}';
    }
}
