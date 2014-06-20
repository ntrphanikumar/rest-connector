package com.ntrphanikumar.restconnect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntrphanikumar.restconnect.model.User;

@Controller
@RequestMapping("/rest")
public class RestSourceController {

    @RequestMapping(value = "/random-user", method = RequestMethod.GET)
    public @ResponseBody User getRandomUser() {
        return newUser();
    }

    public static User newUser() {
        User user = new User();
        user.setUserid(System.nanoTime());
        user.setFname("Reddy");
        user.setMname("Raja");
        user.setLname("Annareddy");
        return user;
    }
}
