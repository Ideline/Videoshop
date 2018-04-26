package se.video_shop.rental_system.rental_system.controllers;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.video_shop.rental_system.rental_system.entities.Customer;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.RentalHistory;
import se.video_shop.rental_system.rental_system.entities.RentalInfo;
import se.video_shop.rental_system.rental_system.formmodels.FilmRentForm;
import se.video_shop.rental_system.rental_system.formmodels.RegistrationForm;
import se.video_shop.rental_system.rental_system.repositories.CustomerRepository;
import se.video_shop.rental_system.rental_system.repositories.FilmRepository;
import se.video_shop.rental_system.rental_system.repositories.RentalHistoryRepository;
import se.video_shop.rental_system.rental_system.repositories.RentalInfoRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

@Controller
@RequestMapping("/rentalInfo")
public class RentalInfoController {

    private final RentalInfoRepository rentalInfoRepository;
    private final FilmRepository filmRepository;
    private final RentalHistoryRepository rentalHistoryRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RentalInfoController(RentalInfoRepository rentalInfoRepository, FilmRepository filmRepository,
                                RentalHistoryRepository rentalHistoryRepository, CustomerRepository customerRepository) {
        this.rentalInfoRepository = rentalInfoRepository;
        this.filmRepository = filmRepository;
        this.rentalHistoryRepository = rentalHistoryRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("")
    public String getRentalInfo(Model model) {

        System.out.println(rentalInfoRepository.findAll());
        model.addAttribute("rentalInfo", rentalInfoRepository.findAll());
        return "rentalInfo";
    }

    @GetMapping("/customerFilms")
    public String getCustomersFilms(HttpSession session) {

        // TODO: finns det inget smidigare sätt att updatera en sessions attribute?
        Customer c = (Customer)session.getAttribute("customer");
        String ssn = c.getSocialSecurityNumber();
        // TODO: behövs kanske inte? Testa!
        session.removeAttribute("customer");
        c.setRentals(customerRepository.findBySocialSecurityNumber(ssn).getRentals());

        LocalDate today = LocalDate.now();
        List<RentalInfo> films = c.getRentals();

        for(RentalInfo ri : films){

            String dateString = ri.getDueDate();
            LocalDate dueDate = LocalDate.parse(dateString);

            if(dueDate.compareTo(today) < 0){
                ri.setOverdue(true);
            }
        }

        customerRepository.save(c);

        session.setAttribute("customer", c);

        return "rental/customerFilms";
    }

    @Transactional(rollbackOn = Exception.class)
    @GetMapping("/returnFilm/{filmID}")
    public String returnFilm(@PathVariable int filmID) {


        RentalInfo rentalInfo = rentalInfoRepository.findByFilm_FilmID(filmID);
        RentalHistory rentalHistory = new RentalHistory(rentalInfo.getId(), rentalInfo.getCustomer(),
                rentalInfo.getRentalDate(), rentalInfo.getDueDate(), rentalInfo.getFilm(),
                rentalInfo.isOverdue());

        System.out.println("Lämnar tillbakafilm");
        rentalHistoryRepository.save(rentalHistory);
        rentalInfoRepository.delete(rentalInfo);
        //rentalInfoRepository.deleteRentalInfoById(rentalInfo.getId());

        // TODO: fixa meddelande som bekräftar att filmen är återlämnad

        return "redirect:/rentalInfo/customerFilms";
    }

    @GetMapping("/{productNumber}/rent/{filmID}")
    public String rentFilm(@PathVariable int productNumber, @PathVariable int filmID, HttpSession session) {

        Film rentedFilm = filmRepository.findByFilmID(filmID);
        Customer customer = (Customer)session.getAttribute("customer");

        RentalInfo ri = new RentalInfo(rentedFilm, customer);
        rentalInfoRepository.save(ri);

        // TODO: fixa meddelande som bekräftar att filmen är uthyrd

        return "redirect:/film/{productNumber}";
    }

    @GetMapping("/rentFilm")
    public String getRentFilmPage(FilmRentForm filmRentForm, HttpSession session) {

        return "rental/rentPage";
    }

    @GetMapping("/validate")
    public String validateRental(@RequestParam int filmID, Model model) {

        RentalInfo rentalInfo = rentalInfoRepository.findByFilm_FilmID(filmID);
        Film film = filmRepository.findByFilmID(filmID);

        model.addAttribute("exists", film != null);
        model.addAttribute("available", film != null && rentalInfo == null);
        if(film != null) {
            model.addAttribute("img", film.getFilmInfo().getImageUrl());
            model.addAttribute("title", film.getFilmInfo().getTitle());
        }
        model.addAttribute("filmID", filmID);

        return "rental/rentPage";
    }

    @PostMapping("/rentFilm")
    public String rentFilm(@RequestParam int filmID3, HttpSession session) {

        Film rentedFilm = filmRepository.findByFilmID(filmID3);
        RentalInfo rentalInfo = rentalInfoRepository.findByFilm_FilmID(rentedFilm.getFilmID());



        if (rentalInfo == null) {

            Customer customer = (Customer)session.getAttribute("customer");

            RentalInfo ri = new RentalInfo(rentedFilm, customer);
            rentalInfoRepository.save(ri);
            session.removeAttribute("filmID");
            session.setAttribute("available", true);

            return "redirect:/rentalInfo/rentFilm";
        } else if(rentalInfo != null){

            session.setAttribute("available", false);
            return "redirect:/rentalInfo/rentFilm";

        }

        session.setAttribute("available", true);
        return "redirect:/rentalInfo/rentFilm";
//        return "rentalInfo/rentFilm";
    }



    @GetMapping("/overdue")
    public String getOverdueFilms(Model model) {

        LocalDate today = LocalDate.now();
        List<RentalInfo> films = rentalInfoRepository.findAll();
        List<RentalInfo> overdueFilms = new ArrayList<>();

        for(RentalInfo ri : films){

            String dateString = ri.getDueDate();
            LocalDate dueDate = LocalDate.parse(dateString);

            if(dueDate.compareTo(today) < 0){
                ri.setOverdue(true);
                rentalInfoRepository.save(ri);
                overdueFilms.add(ri);
            }
        }

        model.addAttribute("overdueFilms", overdueFilms);

        return "rental/overDueFilms";
    }

}
