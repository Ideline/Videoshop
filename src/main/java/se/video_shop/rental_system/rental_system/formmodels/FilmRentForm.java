package se.video_shop.rental_system.rental_system.formmodels;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import se.video_shop.rental_system.rental_system.entities.Customer;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.RentalInfo;
import se.video_shop.rental_system.rental_system.repositories.FilmRepository;
import se.video_shop.rental_system.rental_system.repositories.RentalInfoRepository;

public class FilmRentForm implements Validator {


    private Integer filmID;
    private FilmRepository filmRepository;
    private RentalInfoRepository rentalInfoRepository;

    public Integer getFilmID() {
        return filmID;
    }

    public FilmRentForm setFilmID(Integer filmID) {
        this.filmID = filmID;
        return this;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return FilmRentForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        FilmRentForm formData = (FilmRentForm) o;

//        Film rentedFilm = filmRepository.findByFilmID(formData.getFilmID());
//        RentalInfo rentalInfo = rentalInfoRepository.findByFilm_FilmID(rentedFilm.getFilmID());
//
//        if(rentalInfo != null){
//
//            errors.rejectValue("filmID", "validation_error.notavailable");
//        }
    }
}
