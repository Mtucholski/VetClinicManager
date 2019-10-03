package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "vet_specialty")
public class VetSpeciality extends BaseEntity {

    @Column(name = "specialty")
    @NotEmpty(message = "specialty cannot be empty")
  @Pattern(regexp = "\\p{IsLatin}+")
    private String vetSpecialtyName;

    public VetSpeciality() {
    }

    public VetSpeciality(@NotEmpty(message = "specialty cannot be empty") @Pattern(regexp = "\\p{IsLatin}+") String vetSpecialtyName) {
        this.vetSpecialtyName = vetSpecialtyName;
    }

    public String getVetSpecialtyName() {
        return vetSpecialtyName;
    }

    public void setVetSpecialtyName(String vetSpecialtyName) {
        this.vetSpecialtyName = vetSpecialtyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VetSpeciality that = (VetSpeciality) o;
        return Objects.equals(vetSpecialtyName, that.vetSpecialtyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vetSpecialtyName);
    }
}
