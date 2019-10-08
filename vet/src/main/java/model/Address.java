package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import rest.AddressDeserializer;
import rest.CustomAddressSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@JsonSerialize(using = CustomAddressSerializer.class)
@JsonDeserialize(using = AddressDeserializer.class)
public class Address extends BaseEntity {

    @Column(name = "city")
    @NotEmpty
    @Size(min = 3, max = 25)
    @Pattern(regexp = "\\p{IsLatin}+")
    private String city;

    @Size(min =3, max = 25)
    @Column(name = "street")
    @Pattern(regexp = "\\p{IsLatin}+")
    @NotEmpty
    private String street;

    @Column(name = "home_number")
    @NotEmpty
    private String homeNumber;

    public Address() {
    }

    public Address(@NotEmpty @Size(min = 3, max = 25) @Pattern(regexp = "\\p{IsLatin}+") String city, @Size(min = 3, max = 25) @Pattern(regexp = "\\p{IsLatin}+") @NotEmpty String street, @NotEmpty String homeNumber) {
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                '}';
    }
}
