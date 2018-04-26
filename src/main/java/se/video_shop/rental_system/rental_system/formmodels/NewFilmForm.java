package se.video_shop.rental_system.rental_system.formmodels;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

public class NewFilmForm implements Validator {

    @NotNull(message = "*Välj ett format")
    private String format;

    public String getFormat() {
        return format;
    }

    public NewFilmForm setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return NewFilmForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        NewFilmForm newFilmForm = (NewFilmForm) o;
    }
}
