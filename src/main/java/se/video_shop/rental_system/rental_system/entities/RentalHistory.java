package se.video_shop.rental_system.rental_system.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class RentalHistory {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_socialSecurityNumber")
    private Customer customer;
    @Column(nullable = false)
    private String rentalDate;
    @Column(nullable = false)
    private String dueDate;
    @Column(nullable = false)
    private String returnDate;
    @OneToOne
    @JoinColumn(name = "film_filmID")
    private Film film;

    protected RentalHistory(){}

    public RentalHistory(Long id, Customer customer, String rentalDate, String dueDate,
                         Film film) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY MM dd");
        this.id = id;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.returnDate = formatter.format(LocalDate.now());
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
