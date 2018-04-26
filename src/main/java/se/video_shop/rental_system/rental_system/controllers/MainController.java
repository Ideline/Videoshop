package se.video_shop.rental_system.rental_system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.video_shop.rental_system.rental_system.entities.Customer;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("")
    public String getCustomers(Model model) {

        return "layout/main";
    }

}
