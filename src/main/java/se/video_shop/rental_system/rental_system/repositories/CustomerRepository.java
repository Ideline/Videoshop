package se.video_shop.rental_system.rental_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.video_shop.rental_system.rental_system.entities.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    Customer findBySocialSecurityNumber(String socialSecurityNumber);
    List<Customer> findAllByFirstName(String firstName);
    List<Customer> findAllByLastName(String lastName);
}
