package se.video_shop.rental_system.rental_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.video_shop.rental_system.rental_system.entities.RentalHistory;

public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Long> {
}
