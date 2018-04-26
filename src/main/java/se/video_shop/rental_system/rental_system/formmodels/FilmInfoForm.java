package se.video_shop.rental_system.rental_system.formmodels;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import se.video_shop.rental_system.rental_system.entities.Film;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FilmInfoForm implements Validator {

    @Size(min=1, max=255)
    private String title;
    @Size(min=1, max=255)
    private String genre;
    @Size(min=1, max=1000)
    private String description;
    @Size(min=1, max=255)
    private String releaseDate;
    @Size(min=1, max=255)
    private String ageLimit;
    @Size(min=1, max=255)
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public FilmInfoForm setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public FilmInfoForm setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FilmInfoForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public FilmInfoForm setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public FilmInfoForm setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FilmInfoForm setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilmInfoForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) { FilmInfoForm formData = (FilmInfoForm)o;
    }
}
