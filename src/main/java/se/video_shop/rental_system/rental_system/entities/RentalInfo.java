package se.video_shop.rental_system.rental_system.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class RentalInfo {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_socialSecurityNumber")
    private Customer customer;
    @Column(nullable = false)
    private String rentalDate;
    @Column(nullable = false)
    private String dueDate;
    @OneToOne
    @JoinColumn(name = "film_filmID")
    private Film film;

    protected RentalInfo(){}

    public RentalInfo(Film film, Customer customer) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        this.customer = customer;
        this.film =  film;
        this.rentalDate = formatter.format(LocalDate.now());
        this.dueDate = formatter.format(LocalDate.now().plusDays(1));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
