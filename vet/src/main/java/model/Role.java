package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

public class Role {

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "role")
    @Size(min = 3, max = 25)
    @Pattern(regexp = "\\p{IsLatin}+")
    @NotEmpty(message = "can't be empty")
    private String name;

    public Role() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
