package se.video_shop.rental_system.rental_system.formmodels;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Size;

public class CustomerSearchForm implements Validator {

    //@Size(min=11, max=11)
    private String ssn;
    @Size(max=50)
    private String fName;
    @Size(max=50)
    private String lName;

    public String getSsn() {
        return ssn;
    }

    public CustomerSearchForm setSsn(String ssn) {
        this.ssn = ssn;
        return this;
    }

    public String getFName() {
        return fName;
    }

    public CustomerSearchForm setFName(String fName) {
        this.fName = fName;
        return this;
    }

    public String getLName() {
        return lName;
    }

    public CustomerSearchForm setLName(String lName) {
        this.lName = lName;
        return this;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        CustomerSearchForm formData = (CustomerSearchForm)o;

        if(formData.getSsn().length() != 0 &&
                formData.getSsn().length() != 11) {
            errors.rejectValue("ssn", "validation_error.ssn");
        }
    }
}
