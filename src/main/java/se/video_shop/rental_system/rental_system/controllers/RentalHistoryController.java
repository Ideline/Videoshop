package se.video_shop.rental_system.rental_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.video_shop.rental_system.rental_system.entities.Customer;
import se.video_shop.rental_system.rental_system.repositories.CustomerRepository;
import se.video_shop.rental_system.rental_system.repositories.RentalHistoryRepository;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/rentalHistory")
public class RentalHistoryController {

    private final RentalHistoryRepository rentalHistoryRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RentalHistoryController(RentalHistoryRepository rentalHistoryRepository, CustomerRepository customerRepository) {
        this.rentalHistoryRepository = rentalHistoryRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("")
    public String getRentalHistory(Model model){

        System.out.println(rentalHistoryRepository.findAll());
        model.addAttribute("rentalHistory", rentalHistoryRepository.findAll());
        return "rentalHistory";
    }

    @GetMapping("/customerFilms")
    public String getCustomersFilmHistory(HttpSession session) {

        // TODO: finns det inget smidigare sätt att updatera en sessions attribute?
        Customer c = (Customer)session.getAttribute("customer");
        String ssn = c.getSocialSecurityNumber();
        session.removeAttribute("customer");
        c.setHistoryRentals(customerRepository.findBySocialSecurityNumber(ssn).getHistoryRentals());
        session.setAttribute("customer", c);

        return "rental/customerHistory";
    }
}
