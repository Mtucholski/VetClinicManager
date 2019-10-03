package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * domain object for representing user.
 * @author mateusztucholski
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    @Pattern(regexp = "\\p{IsLatin}+")
    @NotEmpty(message = "username must be filled")
    @Size(min = 4, max = 15)
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "password must be filled")
    @Size(min = 8, max = 20)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER,targetEntity =Role.class, orphanRemoval = true)
    private Set<Role> roles;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @JsonIgnore
    public void addRole(String roleName){

        if (this.roles == null){

            this.roles = new HashSet<>();
        }

        Role role = new Role();
        role.setName(roleName);
        this.roles.add(role);
    }
}
