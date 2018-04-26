package se.video_shop.rental_system.rental_system.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class FilmInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productNumber;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String description;
    @Column(nullable = false)
    private String releaseDate;
    @Column(nullable = false)
    private String ageLimit;
    @Column(nullable = false)
    private String imageUrl;

    // TODO: problem här??
    @OneToMany(mappedBy = "filmInfo", cascade = CascadeType.ALL)
    private List<Film> films;

    protected FilmInfo(){}

    public FilmInfo(String title, String genre, String description, String releaseDate, String ageLimit, String imageUrl) {

        this.title = title;
        this.genre = genre;
        this.description = description;
        this.releaseDate = releaseDate;
        this.ageLimit = ageLimit;
        this.imageUrl = imageUrl;
    }

    public int getProductNumber() {
        return productNumber;
    }

    // TODO: se över setters för autoIncrement
    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

/*public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }*/
}
