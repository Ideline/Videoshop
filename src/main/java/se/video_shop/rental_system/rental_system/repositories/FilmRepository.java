package se.video_shop.rental_system.rental_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.FilmInfo;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer>{

    List<FilmInfo> findAllByFormat(String format);
    Film findByFilmID(int filmID);
}
