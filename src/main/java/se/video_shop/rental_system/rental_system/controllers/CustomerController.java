package se.video_shop.rental_system.rental_system.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.video_shop.rental_system.rental_system.entities.Customer;
import se.video_shop.rental_system.rental_system.entities.Film;
import se.video_shop.rental_system.rental_system.entities.FilmInfo;
import se.video_shop.rental_system.rental_system.entities.RentalInfo;
import se.video_shop.rental_system.rental_system.formmodels.CustomerSearchForm;
import se.video_shop.rental_system.rental_system.formmodels.FilmRentForm;
import se.video_shop.rental_system.rental_system.formmodels.FilmSearchForm;
import se.video_shop.rental_system.rental_system.formmodels.RegistrationForm;
import se.video_shop.rental_system.rental_system.repositories.CustomerRepository;
import se.video_shop.rental_system.rental_system.repositories.FilmRepository;
import se.video_shop.rental_system.rental_system.repositories.RentalInfoRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final RentalInfoRepository rentalInfoRepository;
    private final FilmRepository filmRepository;

    //@Autowired
    public CustomerController(CustomerRepository customerRepository,
                              RentalInfoRepository rentalInfoRepository,
                              FilmRepository filmRepository) {

        this.customerRepository = customerRepository;
        this.rentalInfoRepository = rentalInfoRepository;
        this.filmRepository =filmRepository;
    }

    @GetMapping("/createOrFind/{filmID}")
    public String createOrFindCustomer(@PathVariable int filmID, HttpSession session, RegistrationForm registrationForm) {

        session.setAttribute("filmID", filmID);

        return "redirect:/customer/searchandregister";
    }


    @GetMapping("")
    public String getCustomers(Model model) {
        System.out.println(customerRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @GetMapping("/logout")
    public String logOutCustomer(HttpSession session, HttpServletRequest request) {


        Customer customer = (Customer)session.getAttribute("customer");
        String ssn = customer.getSocialSecurityNumber();
        session.removeAttribute("customer");
        String referer = request.getHeader("Referer").replace("http://www.bortakvall.com", "");

        if(referer.equals("/customer/" + ssn) || referer.equals("/rentalInfo/customerFilms") ||
                referer.equals("/rentalHistory/customerFilms") || referer.equals("/rentalInfo/rentFilm")){
            return "redirect:/customer/searchandregister";
        }

        return "redirect:" + referer;
    }

    @PostMapping("/searchandregister")
    public String register(@Valid RegistrationForm registrationForm, BindingResult result) {

        registrationForm.validate(registrationForm, result);

        if (result.hasErrors()) {
            return "customer/registration";
        }
        Customer c = new Customer(registrationForm.getSocialSecurityNumber(), registrationForm.getFirstName(),
                registrationForm.getLastName(), registrationForm.getAddress(), registrationForm.getZipCode(),
                registrationForm.getCity(), registrationForm.getCountry(), registrationForm.getPhone(),
                registrationForm.getEmail());
        customerRepository.save(c);
        return "redirect:/customer/" + registrationForm.getSocialSecurityNumber();
    }

//    @PostMapping("register")
//    public String register(@Valid RegistrationForm registrationForm, BindingResult result) {
//
//        registrationForm.validate(registrationForm, result);
//
//        if (result.hasErrors()) {
//            return "customer/registration";
//        }
//        Customer c = new Customer(registrationForm.getSocialSecurityNumber(), registrationForm.getFirstName(),
//                registrationForm.getLastName(), registrationForm.getAddress(), registrationForm.getZipCode(),
//                registrationForm.getCity(), registrationForm.getCountry(), registrationForm.getPhone(),
//                registrationForm.getEmail());
//        customerRepository.save(c);
//        return "redirect:/customer/" + registrationForm.getSocialSecurityNumber();
//    }

    @GetMapping("/searchandregister")
    public String register(RegistrationForm registrationForm, CustomerSearchForm customerSearchForm) {

        return "customer/registration";
    }

    @PostMapping("/edit")
    public String edit(@Valid RegistrationForm registrationForm, BindingResult result) {

        registrationForm.validate(registrationForm, result);

        if (result.hasErrors()) {
            return "customer/edit";
        }
        Customer c = new Customer(registrationForm.getSocialSecurityNumber(), registrationForm.getFirstName(),
                registrationForm.getLastName(), registrationForm.getAddress(), registrationForm.getZipCode(),
                registrationForm.getCity(), registrationForm.getCountry(), registrationForm.getPhone(),
                registrationForm.getEmail());

        Customer c2 = customerRepository.findBySocialSecurityNumber(registrationForm.getSocialSecurityNumber());
        c2.setFirstName(c.getFirstName());
        c2.setLastName(c.getLastName());
        c2.setAddress(c.getAddress());
        c2.setZipCode(c.getZipCode());
        c2.setCity(c.getCity());
        c2.setCountry(c.getCountry());
        c2.setPhone(c.getPhone());
        c2.setEmail(c.getEmail());

        customerRepository.save(c2);

        //customerRepository.save(c);
        return "redirect:/customer/" + registrationForm.getSocialSecurityNumber();
    }

    @GetMapping("/edit")
    public String edit(RegistrationForm registrationForm) {

        return "customer/edit";
    }

    @GetMapping("/{socialSecurityNumber}")
    public String customerInfo(@PathVariable String socialSecurityNumber, Model model, FilmRentForm filmRentForm, HttpSession session) {

        Customer c = customerRepository.getOne(socialSecurityNumber);

        session.setAttribute("customer", c);
        //String customerString = c.getFirstName() + " " + c.getLastName() + " " + c.getSocialSecurityNumber();

        List<RentalInfo> rentalList = rentalInfoRepository.findAllByCustomer_SocialSecurityNumber(socialSecurityNumber);

        model.addAttribute("customer", c);
        //model.addAttribute("c", customerString);
        model.addAttribute("ssn", socialSecurityNumber);
        model.addAttribute("rentals", rentalList);
        return "customer/info";
    }

//    @PostMapping("/{socialSecurityNumber}")
//    public String rentFilm(@PathVariable String socialSecurityNumber, @Valid FilmRentForm filmRentForm, BindingResult result) {
//
//        // TODO: fixa så att vi får rätt felmeddelande om fältet inte fylls i eller om det inte finns nån sökträff
//        // TODO: fixa eventuellt att vi får en sökträff tillbaka och att man måste validera att det är den filmen man vill hyra ut.
//        filmRentForm.validate(filmRentForm, result);
//
//        if (result.hasErrors()) {
//            return "customer/info";
//        }
//
//        if(filmRentForm.getFilmID() != null){
//            Customer c = customerRepository.getOne(socialSecurityNumber);
//            //session.setAttribute("customer", c);
//            Film film = filmRepository.findByFilmID(filmRentForm.getFilmID());
//            RentalInfo rentalInfo = new RentalInfo(film, c);
//            rentalInfoRepository.save(rentalInfo);
//            return "redirect:/customer/" + socialSecurityNumber;
//        }
//
//        return "customer/info";
//    }

    @GetMapping("/search")
    public String search(CustomerSearchForm customerSearchForm) {

        return "customer/search";
    }

//    @PostMapping("/search")
//    public String search(@Valid CustomerSearchForm customerSearchForm, BindingResult result, Model model) {
//
//        customerSearchForm.validate(customerSearchForm, result);
//
//        int ssnLenght = customerSearchForm.getSocialSecurityNumber().length();
//        int firstNameLenght = customerSearchForm.getFirstName().length();
//        int lastNameLenght = customerSearchForm.getLastName().length();
//
//        if (result.hasErrors()) {
//            return "customer/search";
//        } else if (ssnLenght == 11) {
//
//            // TODO: Fixa så att man får felmeddelandet: "Ingen sökträff" om det inte finns några träffar.
//            //TODO: Fixa validering i frontend samt regex för personnummer
//
//            List<Customer> searchResults = new ArrayList<>();
//
//
//            searchResults.add(customerRepository.findBySocialSecurityNumber(customerSearchForm.getSocialSecurityNumber()));
//            model.addAttribute("searchResults", searchResults);
//            model.addAttribute("searchWords", customerSearchForm.getSocialSecurityNumber());
//
//            return "customer/searchResult";
//
//        } else if (firstNameLenght != 0 || lastNameLenght != 0) {
//
//            List<Customer> searchResults = new ArrayList<>();
//
//            String searchWords = customerSearchForm.getFirstName() + " " + customerSearchForm.getLastName();
//
//            if (firstNameLenght != 0) {
//                searchResults = addSearchResults(searchResults, "firstName", customerSearchForm.getFirstName());
//            }
//            if (lastNameLenght != 0) {
//                searchResults = addSearchResults(searchResults, "lastName", customerSearchForm.getLastName());
//            }
//            model.addAttribute("searchResults", searchResults);
//            model.addAttribute("searchWords", searchWords);
//
//            return "customer/searchResult";
//        }
//
//        return "customer/search";
//    }

    @PostMapping("/search")
    public String search(@Valid CustomerSearchForm customerSearchForm, BindingResult result, Model model) {

        customerSearchForm.validate(customerSearchForm, result);

        int ssnLenght = customerSearchForm.getSsn().length();
        int firstNameLenght = customerSearchForm.getFName().length();
        int lastNameLenght = customerSearchForm.getLName().length();

        if (result.hasErrors()) {
            return "customer/search";
        } else if (ssnLenght == 11) {

            // TODO: Fixa så att man får felmeddelandet: "Ingen sökträff" om det inte finns några träffar.
            //TODO: Fixa validering i frontend samt regex för personnummer

            List<Customer> searchResults = new ArrayList<>();


            searchResults.add(customerRepository.findBySocialSecurityNumber(customerSearchForm.getSsn()));
            model.addAttribute("searchResults", searchResults);
            model.addAttribute("searchWords", customerSearchForm.getSsn());

            return "customer/searchResult";

        } else if (firstNameLenght != 0 || lastNameLenght != 0) {

            List<Customer> searchResults = new ArrayList<>();

            String searchWords = customerSearchForm.getFName() + " " + customerSearchForm.getLName();

            if (firstNameLenght != 0) {
                searchResults = addSearchResults(searchResults, "fName", customerSearchForm.getFName());
            }
            if (lastNameLenght != 0) {
                searchResults = addSearchResults(searchResults, "lName", customerSearchForm.getLName());
            }
            model.addAttribute("searchResults", searchResults);
            model.addAttribute("searchWords", searchWords);

            return "customer/searchResult";
        }

        return "customer/search";
    }

    public List<Customer> addSearchResults(List<Customer> searchResults, String type, String name) {

        if (type.equals("fName")) {

            for (Customer c : customerRepository.findAllByFirstName(name)) {
                searchResults.add(c);
            }

        } else if (type.equals("lName")) {

            for (Customer c : customerRepository.findAllByLastName(name)) {
                searchResults.add(c);
            }
        }

        return searchResults;
    }

//    @GetMapping("/searchresult")
//    public String getSearchResult(/*List<FilmInfo> films, Model model*/) {
//
//        return "film/searchResult";
//    }

//    @GetMapping("/info")
//    public String getCustomerInfo(Model model) {
//
//        return "customer/info";
//    }
}
