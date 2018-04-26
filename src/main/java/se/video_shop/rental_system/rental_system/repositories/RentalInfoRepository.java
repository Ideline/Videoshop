package se.video_shop.rental_system.rental_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.RentalInfo;

import javax.transaction.Transactional;
import java.util.List;


public interface RentalInfoRepository extends JpaRepository<RentalInfo, Long> {

    List<RentalInfo> findAllByCustomer_SocialSecurityNumber(String ssn);
    RentalInfo findByFilm_FilmID(int filmID);

    @Modifying
    Long deleteRentalInfoById(Long id);

    List<RentalInfo> findAllByOverdue(boolean overdue);
}
