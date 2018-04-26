package se.video_shop.rental_system.rental_system.entities;

import javax.persistence.*;

@Entity
public class Film {

    @Id
    @GeneratedValue
    private int filmID;
    @Column(nullable = false)
    private String format;
    @ManyToOne
    private FilmInfo filmInfo;
    @OneToOne(mappedBy = "film")
    private RentalInfo rentalInfo;

    protected Film(){}

    public Film(FilmInfo filmInfo, String format) {
        this.filmInfo = filmInfo;
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public FilmInfo getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(FilmInfo filmInfo) {
        this.filmInfo = filmInfo;
    }

    public void setRentalInfo(RentalInfo rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    public RentalInfo getRentalInfo() {
        return rentalInfo;
    }
}
