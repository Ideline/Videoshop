package se.video_shop.rental_system.rental_system.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se.video_shop.rental_system.rental_system.entities.Customer;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.FilmInfo;

import java.util.List;

public interface FilmInfoRepository extends JpaRepository<FilmInfo, Integer> {

//    List<FilmInfo> findByTitleContains(String title);
//    List<FilmInfo> findAllByGenre(String genre);
//    List<FilmInfo> findByTitleContainsAndGenre(String title, String genre);
//    List<FilmInfo> findByProductNumber(int productNumber);

    Page<FilmInfo> findByTitleContains(String title, Pageable pageable);
//    Page<FilmInfo> findByTitleContainsAndGenre(String title, List<String> genre, Pageable pageable);
    Page<FilmInfo> findAllByGenre(String genre, Pageable pageable);
    Page<FilmInfo> findByProductNumber(int productNumber, Pageable pageable);
    Page<FilmInfo> findAllByGenreIn(List<String> genres, Pageable pageable);
//    Page<FilmInfo> findAllByGenreInAndTitleContains(List<String> genres, Pageable pageable);
    Page<FilmInfo> findAllByTitleContainsAndGenreIn(String title, List<String> genres, Pageable pageable);
}
