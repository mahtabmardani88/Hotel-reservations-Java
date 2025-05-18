package com.application.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MvcController {

    // http://localhost:9191/home
    @GetMapping("/home")
    public String homePage() {

        System.out.println("Inside homePage");

        return "home";
    }
    @GetMapping("/homecn")
    public String homecnPage() {

        System.out.println("Inside homecnPage");

        return "homecn";
    }

    // http://localhost:9191/room
    @GetMapping("/rooms")
    public String roommPage() {

        System.out.println("Inside roomPage");

        return "rooms";
    }

    @GetMapping("/contact")
    public String contactPage() {

        System.out.println("Inside contactPage");

        return "contact";
    }
    @GetMapping("/reservation")
    public String bookingPage() {

        System.out.println("Inside bookingPage");

        return "reservation";
    }
    @GetMapping("/roomservices")
    public String roomservicesPage() {

        System.out.println("Inside roomservicesPage");

        return "roomservices";
    }

    @GetMapping("/login")
    public String loginPage() {

        System.out.println("Inside loginPage");

        return "login";
    }

    // http://localhost:9191/forgotpassword
    @GetMapping("/forgotpassword")
    public String forgotPasswordPage() {

        System.out.println("Inside forgotPasswordPage");

        return "forgotpassword";
    }
    // http://localhost:9191/guest


    @GetMapping("/reservationcn")
    public String bookingcnPage() {

        System.out.println("Inside bookingcnPage");

        return "reservationcn";
    }

    @GetMapping("/roomscn")
    public String roomscnPage() {

        System.out.println("Inside roomsPage");

        return "roomscn";
    }
    @GetMapping("/contactcn")
    public String contactcnPage() {

        System.out.println("Inside contactcnPage");

        return "contactcn";
    }
    @GetMapping("/logincn")
    public String logincnPage() {

        System.out.println("Inside logincnPage");

        return "logincn";
    }
    @GetMapping("/roomservicescn")
    public String roomsevicescnPage() {

        System.out.println("Inside roomservicescnPage");

        return "roomservicescn";
    }


    // http://localhost:9191/register
    @GetMapping("/register")
    public String registerPage() {

        System.out.println("Inside registerPage");

        return "register";
    }
    // http://localhost:9191/reservationguest
    @GetMapping("/reservationguest")
    public String reservationGuestPage() {

        System.out.println("Inside reservationGuestPage");

        return "reservationguest";
    }

    @GetMapping("/admin")
    public String adminPage() {

        System.out.println("Inside admin");

        return "adminmenu";
    }

    // http://localhost:9191/discount
    @GetMapping("/discount")
    public String discountPage() {

        System.out.println("Inside discountPage");

        return "discount";
    }


    // http://localhost:9191/reservation
    @GetMapping("/reservationadmin")
    public String reservationPage() {

        System.out.println("Inside reservationPage");

        return "reservationadmin";
    }

    // http://localhost:9191/room
    @GetMapping("/room")
    public String roomPage() {

        System.out.println("Inside roomPage");

        return "roomadmin";
    }
    // http://localhost:9191/guest
    @GetMapping("/guest")
    public String guestPage() {

        System.out.println("Inside guestPage");

        return "guest";
    }
    // http://localhost:9191/admin
    @GetMapping("/restaurant")
    public String restaurantPage() {

        System.out.println("Inside restaurantPage");

        return "restaurant";
    }


}

