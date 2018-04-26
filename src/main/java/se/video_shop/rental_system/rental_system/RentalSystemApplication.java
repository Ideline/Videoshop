package se.video_shop.rental_system.rental_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.video_shop.rental_system.rental_system.entities.Customer;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.FilmInfo;
import se.video_shop.rental_system.rental_system.entities.RentalInfo;
import se.video_shop.rental_system.rental_system.repositories.CustomerRepository;
import se.video_shop.rental_system.rental_system.repositories.FilmInfoRepository;
import se.video_shop.rental_system.rental_system.repositories.FilmRepository;
import se.video_shop.rental_system.rental_system.repositories.RentalInfoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class RentalSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(RentalSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RentalInfoRepository rentalInfoRepository,
                                        FilmInfoRepository filmInfoRepository,
                                        CustomerRepository customerRepository,
                                        FilmRepository filmRepository){

        return args -> {

            LocalDate releaseDate1 = LocalDate.of(2018, 01, 01);
            LocalDate releaseDate2 = LocalDate.of(2018, 02, 01);

            Customer c1 = new Customer("811221-3940","Anna-Karin","Friberg", "Fersens väg 4", "211 42","Malmö","Sweden", "0721869005","akarinwest@gmail.com");
            Customer c2 = new Customer("880401-4767","Robin","Larsson", "Birger jarlsgatan 41D", "216 11","Malmö","Sweden", "0700392267","Robin@gmail.com");
            customerRepository.save(c1);
            customerRepository.save(c2);

/*
            FilmInfo filmInfo1 = new FilmInfo("Film1", "Komedi", "Bästa filmen", releaseDate1, "DVD");
            FilmInfo filmInfo2 = new FilmInfo( "Film2", "Action", "Näst bästa filmen", releaseDate2, "Blue Ray");
            Film film1 = new Film(filmInfo1);
            Film film2 = new Film(filmInfo1);
            Film film3 = new Film(filmInfo2);

            List<Film> films = new ArrayList<Film>(){{
                add(film1);
                add(film2);
                add(film3);
            }};

            filmInfo1.setFilms(films);

            filmRepository.saveAll(films);

            filmInfoRepository.save(filmInfo1);
            filmInfoRepository.save(filmInfo2);

            RentalInfo rInfo1 = new RentalInfo(film1,c1);
            rentalInfoRepository.save(rInfo1);
            RentalInfo rInfo2 = new RentalInfo(film2,c2);
            rentalInfoRepository.save(rInfo2);
            RentalInfo rInfo3 = new RentalInfo(film3,c2);
            rentalInfoRepository.save(rInfo3);*/

        };
    }
}
