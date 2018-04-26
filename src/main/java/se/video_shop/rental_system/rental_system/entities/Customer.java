package se.video_shop.rental_system.rental_system.entities;




import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    private String socialSecurityNumber;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String phone;
    private String email;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<RentalInfo> rentals;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<RentalHistory> HistoryRentals;

    protected Customer(){

    }

    public Customer(String socialSecurityNumber, String firstName, String lastName, String address, String zipCode, String city, String country, String phone, String email) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RentalInfo> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalInfo> rentals) {
        this.rentals = rentals;
    }

    public List<RentalHistory> getHistoryRentals() {
        return HistoryRentals;
    }

    public void setHistoryRentals(List<RentalHistory> historyRentals) {
        HistoryRentals = historyRentals;
    }
}
