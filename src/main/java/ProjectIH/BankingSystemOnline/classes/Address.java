package ProjectIH.BankingSystemOnline.classes;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    @Digits(integer=6, fraction=0)
    private int postalCode;
    @NotNull
    private String country;


              //constructors, getters & setters:


    public Address() {}

    public Address(String street, String city, int postalCode, String country) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
