package se.video_shop.rental_system.rental_system.formmodels;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.OptionalInt;

public class FilmSearchForm implements Validator {

    private Integer productNumber;
    @Size(max=255)
    private String title;
    private String genre;
    private String format;

    public Integer getProductNumber() {
        return productNumber;
    }

    public FilmSearchForm setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FilmSearchForm setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public FilmSearchForm setGenre(String genre) {
        this.genre = genre;
        return this;
    }


    public String getFormat() {
        return format;
    }

    public FilmSearchForm setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilmSearchForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FilmSearchForm formData = (FilmSearchForm)o;
    }
}
