package se.video_shop.rental_system.rental_system.formmodels;


import org.springframework.validation.Errors;

import javax.servlet.Registration;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationForm implements org.springframework.validation.Validator {


    @Size(min=11, max=11, message = "Kräver formatet ÅÅMMDD-XXXX")
    private String socialSecurityNumber;
    @Size(min=1, max=255)
    private String firstName;
    @Size(min=1, max=255)
    private String lastName;
    @Size(min=1, max=255)
    private String address;
    @Size(min=1, max=6)
    private String zipCode;
    @Size(min=1, max=255)
    private String city;
    @Size(min=1, max=255)
    private String country;
    @Size(min=1, max=255)
    private String phone;
    @Size(min=1, max=255)
    private String email;

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public RegistrationForm setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegistrationForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegistrationForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RegistrationForm setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public RegistrationForm setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public RegistrationForm setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public RegistrationForm setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegistrationForm setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationForm setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RegistrationForm formData = (RegistrationForm)o;
    }
}
