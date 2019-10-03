package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * java bean with id and name property. Used as base for other entities
 * @author mateusztucholski
 */
@MappedSuperclass
public class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    /**
     * generated value from sequence in database.
     */
    protected Long id;

    @Column(name = "name")
    @NotEmpty
    protected String name;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
