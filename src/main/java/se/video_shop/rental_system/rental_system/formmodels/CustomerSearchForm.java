package se.video_shop.rental_system.rental_system.formmodels;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Size;

public class CustomerSearchForm implements Validator {

    //@Size(min=11, max=11)
    private String socialSecurityNumber;
    @Size(max=50)
    private String firstName;
    @Size(max=50)
    private String lastName;

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public CustomerSearchForm setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerSearchForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerSearchForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        CustomerSearchForm formData = (CustomerSearchForm)o;

        if(formData.getSocialSecurityNumber().length() != 0 &&
                formData.getSocialSecurityNumber().length() != 11) {
            errors.rejectValue("socialSecurityNumber", "validation_error.ssn");
        }
    }
}
